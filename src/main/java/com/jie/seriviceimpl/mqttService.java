package com.jie.seriviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jie.bean.Coordinates;
import com.jie.bean.Point;
import com.jie.dao.BathMapper;
import com.jie.dao.DeviceMapper;
import com.jie.service.IMqttService;
import com.jie.service.UserService;
import org.fusesource.mqtt.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.*;

@Service
public class mqttService implements IMqttService {
    private MQTT mqtt;
    private FutureConnection connection;
    private Map<Integer,Thread> threadPool;//线程池
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    BathMapper bathMapper;
    @Autowired
    UserService userService;
    public static Topic[] topics = {
            //new Topic("bath10001p", QoS.EXACTLY_ONCE),
            new Topic("Device/Public/#", QoS.AT_MOST_ONCE),
            new Topic("DeviceStatus/#", QoS.AT_MOST_ONCE)};//采用startDevice/bath10001的方式来订阅所有设备的打开与否，并要求硬件发送如{"status":1}的方式告知服务器打开设备

    mqttService() {
        mqtt = new MQTT();
        threadPool=new HashMap();
        try {
            mqtt.setHost("47.100.47.134", 1883);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //mqtt.setCleanSession(false);
        mqtt.setUserName("admin");//服务器认证用户名
        mqtt.setPassword("public");//服务器认证密码
        // 获取mqtt的连接对象BlockingConnection
        connection = mqtt.futureConnection();
        connection.connect();
        new Thread() {
            @Override
            public void run() {
                try {
                    //硬件发送数据,
                    connection.subscribe(topics);
                    while (true) {
                        Future<Message> futrueMessage = connection.receive();
                        Message message = futrueMessage.await();
                        String topic = message.getTopic();
                        String json = String.valueOf(message.getPayloadBuffer());
                        json = json.substring(json.indexOf("{"));
                        JSONObject jsonObject = JSONObject.parseObject(json);
                        String sub = topic.substring(topic.lastIndexOf("/") + 1);
                        Integer subId = Integer.valueOf(sub);
                        System.out.println("主题："+topic+"\n 内容"+json);
                        if (topic.contains("DeviceStatus/")) {//如多包含打开设备的格式,则订阅,DeviceStatus/10001
                            updateDeviceStatusFromDevice(subId,jsonObject);
                            continue;
                        }
                        //接收设备实时传递的数据
                        //数据格式  topic=newLab/10001   {"
                        //每二十秒接收一次数据
                        //首先查询deviceId
                        if(topic.contains("Device/Public/")){
                            Integer deviceId = deviceMapper.getDeviceIdBySubId(subId);
                            switch (deviceId) {
                                case 1: {//水浴锅
                                    JSONObject sub_obj=null;
                                    try{
                                        sub_obj=jsonObject.getJSONObject("fields");
                                    }catch (NullPointerException e){
                                        e.printStackTrace();
                                        continue;
                                    }
                                    double cv =-1;
                                    try{
                                        cv=sub_obj.getDouble("CV");//当前温度,相对
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        continue;
                                    }
                                      cv = (double) Math.round(cv* 100) / 100;
                                     double ct = sub_obj.getDouble("CT");//当前时间，相对  单位minute\
                                      ct = (double) Math.round(ct* 100) / 100;
                                    //首先获取数据库中保存的坐标系点
                                    Coordinates rawCoordinate = bathMapper.getCoordinates(subId);
                                    List<Point> rawPoints=rawCoordinate.getCoordinates();
                                    System.out.println("数据库中coordinates"+JSON.toJSON(rawCoordinate).toString());
                                    String newPoints;
                                    if (rawPoints == null || rawPoints.size()==0) {
                                        //直接放入
                                        rawPoints=new ArrayList<>();
                                        Point origin = new Point(0.0, 0.0);
                                        Point point = new Point(ct, cv);
                                        rawPoints.add(origin);
                                        rawPoints.add(point);
                                    } else {
                                        //判断上个坐标和将插入的数据的区间
                                        double interval=ct-rawPoints.get(rawPoints.size()-1).getCt();
                                        if (interval<0.25){//如果数据间隔小于十五秒，则不进行处理
                                            break;
                                        }
                                        //将获取的点加入points
                                        rawPoints.add(new Point(ct,cv));
                                    }
                                    Map map = new HashMap();
                                    map.put("subId", subId);
                                    map.put("coordinate", JSON.toJSON(rawPoints).toString());
                                    //上传到数据库
                                    if ( bathMapper.insertCoordinate(subId,JSON.toJSON(rawPoints).toString()) == 1) {//上传更新成功
                                        System.out.println("points更新成功");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private boolean updateDeviceStatusFromDevice(int subId,JSONObject object) {//更新状态等
        //首先查询deviceId
        if (object==null)
            return false;
        System.out.println("status接受到的数据"+object.toJSONString());
        Integer deviceId = deviceMapper.getDeviceIdBySubId(subId);
        Integer status = object.getInteger("status");
       // Date startTime=object.getDate("startTime");//上传到数据库
        if (deviceId == null) {
            return false;
        }
        switch (deviceId) {
            case 1: {//水浴锅
                Map map = new HashMap();
                map.put("subId", subId);
                map.put("status",status);
                if (status==0){//设备发出关闭信息
                    //map.put("startTime",null);
                    //map.put("coordinates","");
                    //  map.put("sv",0);
                    // map.put("st",0);
                    interruptDevice(subId);
                    //userService.createDeviceHistory(userId,subId);//保存图像
                }else if (status==1){//设备发出开机信息
                   interruptDevice(subId);
                }else  if (status==2){//运行
                    map.put("coordinates","");
                   map.put("startTime",new Date());
                }else if (status==3){//暂停
                }
                return bathMapper.updateBath(map) == 1 ? true : false;
            }
        }
        return false;
    }


    @Override
    public boolean runDevice(int subId,int userId) {
        //线程池得运作方式是键值对
      System.out.println("mqttService开始运行设备");
      Thread t= new Thread(){
            @Override
            public void run() {
                //对str进行处理
                //从数据库中获取points
               String str=bathMapper.getPoints(subId);
                String str1=str.toUpperCase();
                System.out.println(str1);
                List<JSONObject> points= JSONArray.parseArray(str1,JSONObject.class);
                ListIterator<JSONObject> listIterator=points.listIterator();
                try {
                    while (listIterator.hasNext()) {//在这里
                        //睡眠时间由时长决定
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();//现在指针指向前一个
                            JSONObject pObject = listIterator.next();//值是前一个，但指针指向当前
                            JSONObject object = listIterator.next();//值是当前，指针指向下一个
                            JSONObject nowObject = new JSONObject();
                            JSONObject now = new JSONObject();
                            nowObject.put("SV", object.getDoubleValue("CV"));
                            double time = object.getFloatValue("CT") - pObject.getFloatValue("CT");
                            nowObject.put("setTime", time);
                            //nowObject.put("appointment", 0.0);
                            now.put("work_process", nowObject);
                            String pTopic = "Device/Subscribe/" + subId;
                            String json = now.toJSONString();
                            System.out.println("开始mqtt");
                            connection.publish(pTopic, json.getBytes(), QoS.EXACTLY_ONCE, false);
                            long delay = (long) (time * 60 * 1000);
                            sleep(delay);
                        } else {
                            listIterator.next();
                        }
                    }
                    //出循环，说明运行完毕
                    //生成历史记录
                }
                catch (InterruptedException e) {//在这里可以打断线程
                        e.printStackTrace();
                    }
                finally {
                    userService.createDeviceHistory(userId,subId);
                    System.out.println("生成历史纪录");
                }
            }
        };
        t.start();
        threadPool.put(subId,t);
       // connection.publish(pTopic)
        return false;
    }

    @Override
    public void interruptDevice(int subId) {
        //如果当前设备有线程在运行，则打断该线程
         Thread t=threadPool.get(subId);
         if (t!=null){
             t.interrupt();
             threadPool.remove(subId);
         }
    }

    @Override
    public boolean openDevice(int subId) {
        JSONObject cmd=new JSONObject();
        cmd.put("cmd","run");
        connection.publish("Device/Subscribe/"+subId,cmd.toJSONString().getBytes(),QoS.EXACTLY_ONCE, false);
        return true;
    }

    @Override
    public boolean closeDevice(int subId) {
        JSONObject cmd=new JSONObject();
        cmd.put("cmd","stop");
        connection.publish("Device/Subscribe/"+subId,cmd.toJSONString().getBytes(),QoS.EXACTLY_ONCE, false);
        return true;
    }
    @Override
    public boolean sendResultToMobile(int userId,String result) {//发送结果给手机
         String pubTopic="SSM/Public/"+userId;
         connection.publish(pubTopic,result.getBytes(), QoS.EXACTLY_ONCE, false);
        return false;
    }

}



