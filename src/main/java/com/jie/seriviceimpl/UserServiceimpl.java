package com.jie.seriviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jie.bean.*;
import com.jie.dao.BathMapper;
import com.jie.dao.DeviceMapper;
import com.jie.dao.UserMapper;
import com.jie.service.UserService;
import com.jie.service.bathService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.jfree.data.json.impl.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {
    private final static int BATH = 1;
    @Autowired
    private UserMapper mapper = null;
    @Autowired
    private bathService bathService;
    @Autowired
    private DeviceMapper deviceMapper = null;
    @Autowired
    private BathMapper bathMapper=null;
    @Autowired
    private mqttService mqttService=null;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public int insertUser(User user) {
        return mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public User getUser(String id) {
        return mapper.getUser(id);
    }

    @Override
    public User login(User user) {
        User u = getUserByPhone(user.getUserPhone());
        //比对传入的用户手机号和密码是否和数据的匹配
        if (u == null)
            return null;
        if (u.getUserPhone().equals(user.getUserPhone()) && u.getUserPassword().equals(user.getUserPassword())) {
            return u;
        }
        return null;
    }

    @Override
    public User getUserByPhone(String userPhone) {
        return mapper.getUserByPhone(userPhone);
    }

    @Override
    public int register(User user) {
        //插入之前先进行查询，如果已存在则无法插入，并返回msg
        if (mapper.getUserByPhone(user.getUserPhone()) == null) {
            if (mapper.insert(user) == 1)
                return getUserId(user.getUserPhone());
            else return 101;//插入失败
        }
        return 100;//100代表已注册
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public int getUserId(String userPhone) {
        return mapper.getUserId(userPhone);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<myDevice> getDevices(int userId) {
        //找到该用户对应的设备列表
        List<myDevice> devices = mapper.getDevices(userId);
        for (myDevice device : devices) {//找到对应设备的status
            switch (device.getDeviceId()) {
                case 1: {//1代表水浴锅
                    System.out.println(device.getSubId()+ "deviceId");
                    int status = bathService.getBathStatus(device.getSubId());
                    device.setStatus(status);
                }
            }
        }
        return devices;
    }


    @Override
    public boolean UnbundlingDevice(Map map) {
        int row = 0;
        try {
            row = mapper.UnbundlingDevice(map);//如果插入重复的行，则插入失败
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (row == 1)
            return true;
        return false;
    }

    @Override
    public List<myDeviceHistory> selectMyhistoryDevices(int userId) {
        return mapper.selectMyhistoryDevices(userId);
    }

    @Override
    public List<Map> selectMyHistoryDeviceNameAndId(int subId) {
        return mapper.selectMyHistoryDeviceNameAndId(subId);
    }

    @Override
    public boolean updateDevicePosition(int userId, int subId) {
        //先获取要置顶的设备的所在位置
        int position = mapper.getDevicePositionInMyDevices(userId, subId);
        if (position == 0)
            return true;
        System.out.println("位置" + position);
        //两次执行
        int row = 0;
        if ((row = mapper.updateDevicePositionInMyDevices(userId, null, position)) != 0) {
            System.out.println("影响的行数" + row);
            row = mapper.updateDevicePositionInMyDevices(userId, subId, null);
        }
        return (row == 0) ? false : true;
    }

    @Override
    public boolean updateMyDevices(int subId, int userId, String alias) {
        return mapper.updateMyDevices(subId, userId, alias) == 0 ? false : true;
    }

    @Override
    public int insertDevicePoints(int deviceId, int userId, String points) {
        //先获取对应用户对应设备已有的points条数

        return mapper.insertDevicePoints(deviceId, userId, points);
    }

    @Override
    public int deleteDevicePoints(int deviceId, int userId, int position) {
        //删掉device_points中对应的points
        if (mapper.deleteDevicePoints(deviceId, userId, position) == 1)
            return mapper.fixDevicePointsPosition(deviceId, userId, position);
        //调整删除过后的位置
        return 0;
    }

    @Override
    public List<String> selectDevicePoints(int deviceId, int userId) {
        return mapper.selectDevicePoints(deviceId, userId);
    }

    @Override
    public List<PositionAndPoints> selectDevicePositionAndPoints(int deviceId, int userId) {
        return mapper.selectDevicePositionAndPoints(deviceId, userId);
    }

    @Override
    public ArrayList<HashMap> getMyDevicesHistoryIdsBySub(int userId) {
        //首先返回deviceId分组，获取不同subId下的historys
        ArrayList<HashMap> temp = new ArrayList<>();
        temp = mapper.getMyDeviceHistoryIds(userId);//(deviceId,subId,historyIds)
        //首先拆分historyIds字符串
        for (int i = 0; i < temp.size(); i++) {
            ArrayList<Integer> historyIds = new ArrayList<>();
            String[] historyIdsStr = ((String) temp.get(i).get("historyIds")).split(",");
            for (String s : historyIdsStr) {
                historyIds.add(Integer.parseInt(s));
            }
            //填充到temp
            temp.get(i).remove("historyIds");
            temp.get(i).put("historyIds", historyIds);
        }
        return temp;
    }

    @Override
    public String getSubNameById(int userId, int subId) {
        String subName = mapper.getSubNameById(userId, subId);
        //找不到才寻找真名
        return subName;
    }

    @Override
    public HashMap getHistoryByHistoryId(int userId, int historyId) {
        //先根据historyId对应的deviceId，确定在myXXXHistory 表中查找数据
        int deviceId = mapper.getDeviceIdByHistoryId(userId, historyId);
        switch (deviceId) {
            case BATH: {
                return mapper.getBathHistoryByHistoryId(historyId);
            }
        }
        return null;
    }

    @Override
    public HashMap getMyManageDevice(int userId, int subId) {
        return mapper.getMyManageDevice(userId, subId);
    }

    @Override
    public int updateMyManageDevice(Map map) {
        return mapper.updateMyManageDevice(map);
    }

    @Override
    public int deleteDeviceFromMyDevices(Map map) {
        //先调顺序后删除
        int subId = (int) map.get("subId");
        int userId = (int) map.get("userId");
        mapper.updatePositionFromMyDevices(userId, subId);
        return mapper.deleteDeviceFromMyDevices(userId, subId);
    }
    @Override
    public boolean bindDevice(Map map) {
        int row = 0;
        try {
            //首先获取当前position得数量
            int userId = (int) map.get("userId");
            int subId = (int) map.get("subId");
            int deviceId=deviceMapper.getDeviceIdBySubId(subId);
            int position = mapper.getNumberFromMyDevices(userId);
            map.put("position", position);
            map.put("deviceId",deviceId);
            map.put("alias","设备"+(position+1));
            row = mapper.bindDevice(map);//如果插入重复的行，则插入失败
            //判断isManager==true，如果是则在manager添加数据
            boolean isManager = (boolean) map.get("isManager");
            if (isManager) {
                Map manager = new HashMap();
                manager.put("userId", userId);
                manager.put("musers", null);
                manager.put("subId", subId);
                manager.put("isManager", isManager);
                manager.put("authorizedCode", "abc123");//默认
                manager.put("maxCapacity", 20);//默认
                System.out.println( mapper.addManager(manager));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (row == 1)
            return true;
        return false;
    }
    @Override
    public String bindDeviceByAuthorizedCode(Map map) {
        String code=(String)map.get("authorizedCode");
        int userId=(int)map.get("userId");
        Integer subId=mapper.getSubIdFromManagerByauthorizedCode(code);
        //获取对应subI
        //在manager中查找对应
        if (subId==null){
            return  "此连接码不存在";
        }else{
            //添加到myDevices
            Map myDevice=new HashMap();
            myDevice.put("userId",userId);
            myDevice.put("subId",subId);
            myDevice.put("isManager",false);
            if (bindDevice(myDevice))
                return "绑定成功";
            else{
                return "绑定失败";
            }
        }
    }
    @Transactional  //事务注释,涉及到改变数据库的最好都加上
    @Override
    public boolean createDeviceHistory( int userId, int subId)  {//结束运行
        System.out.println("生成历史纪录方法");
        //根据不同的deviceId,生成不同种类的历史纪录
        int deviceId=deviceMapper.getDeviceIdBySubId(subId);
        switch (deviceId){
            case  BATH:{
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                //从bath中获取coordinate和startTime
                Map   map=bathMapper.getCoordinateAndStartTime(subId);
                String coordinate=(String)map.get("coordinate");
                List<Point> points=null;
                try{
                    points= JSON.parseArray(coordinate,Point.class);//解出points
                    if(points==null||points.size()<2){//无须生成历史记录

                    }else {
                        java.util.Date sTime=(java.util.Date)map.get("startTime");
                        if (sTime==null)
                            return false;
                        String startTime=sdf.format(sTime);
                        myDeviceHistory mDeviceHistory=new myDeviceHistory();
                        mDeviceHistory.setSubId(subId);
                        mDeviceHistory.setUserId(userId);
                        int row=mapper.insertMyDeviceHistory(mDeviceHistory);
                        int historyId=mDeviceHistory.getHistoryId();
                        float end=-1;
                        String endTime=null;
                        try{
                            end=(float)points.get(points.size()-1).getCt();
                            long t=sTime.getTime()+(long)(end*60*1000);
                            endTime=sdf.format(new Date(t));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        mapper.insertMyBathHistory(historyId,startTime,endTime,coordinate);
                        //mqtt发送消息告诉客户端，运行结束
                        //user
                        String alias=mapper.getMyAlia(userId,subId);
                        if (alias==null||alias.equals("")){//无别名
                            alias="水浴锅";
                        }
                        JSONObject json=new JSONObject();
                        json.put("title",subId);
                        json.put("text","complete");
                        json.put("date",sdf.format(new java.util.Date()));
                        System.out.println("生成历史纪录结果"+json.toJSONString());
                        mqttService.sendResultToMobile(userId,json.toJSONString());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                ArrayList Points=new ArrayList();
                Points.add(new Point(0.0,0.0));
                Map map1=new HashMap();
                map1.put("subId",subId);
                map1.put("status",1);
                map1.put("coordinates",JSON.toJSON(Points).toString());
               // map1.put("sv",0);
              //  map1.put("st",0);
                map1.put("startTime",null);
                bathMapper.updateBath(map1);
               // mqttService.closeDevice(subId);//关闭设备

                break;
            }
        }
        return false;
    }

    @Override
    public List<Map> getMyAlias(int userId) {
        return mapper.getMyAlias(userId);
    }

    @Override
    public Map getSubHistoryIds(int userId, int subId) {//"subId\":10001,\"historyIds\":[1,2],"text":"a","subName":"a","deviceId":1}
         List historyIds=mapper.getSubHistoryIds(userId,subId);
         int deviceId=deviceMapper.getDeviceIdBySubId(subId);
         Map device=new HashMap();
         device.put("subId",subId);
         device.put("historyIds",historyIds);
         device.put("deviceId",deviceId);
         return device;
    }

    @Override
    public String getMyAlia(int userId, int subId) {
        return mapper.getMyAlia(userId,subId);
    }
}
