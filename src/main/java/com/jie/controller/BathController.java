package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jie.bean.Bath;
import com.jie.bean.Coordinates;
import com.jie.bean.Point;
import com.jie.service.IMqttService;
import com.jie.service.UserService;
import com.jie.service.bathService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bathController")
public class BathController {//设备控制
    @Autowired
    bathService bathService = null;
    @Autowired
    UserService userService= null;
    @Autowired
    IMqttService mqttService = null;
    Logger logger = Logger.getLogger(BathController.class);

    @ResponseBody
    @RequestMapping(value = "/getDevice.json", method = RequestMethod.POST)
    public JSONObject getDevice(@RequestBody Map map) {
        JSONObject jsonObject = new JSONObject();
        /*
            根据deviceId返回具体类型
         */
        Integer deviceId = (Integer) map.get("deviceId");
        Integer subId = (Integer) map.get("subId");
        switch (deviceId) {
            case 00001: {//水浴锅
                Bath bath = bathService.getBath(subId);
                jsonObject.put("device", bath);
                break;
            }
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/settingBath.json", method = RequestMethod.POST)
    public JSONObject updateDevice(@RequestBody Map map) {//设定设备
         /*
           需要做的工作
             更新CV sv st ct 坐标点
             坐标点
          */
        System.out.println(map.get("cv"));
        int subId = (int) map.get("subId");
        Bath bath = bathService.getBath(subId);//获取设备
        double sv = Double.valueOf(map.get("sv").toString());
        double st = Double.valueOf(map.get("st").toString());
        //bath.setSt(st);
        //bath.setSv(sv);
        //重置坐标系
        bath.setCoordinates(null);
        JSONObject object = new JSONObject();
        int row = bathService.settingBath(bath);
        object.put("status", row);//返回给android影响的行数
        logger.info(bath.toString());
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/updateCv.json", method = RequestMethod.POST)
    public int updateCv(String subId, String cv) {
        int id = Integer.parseInt(subId);
        double CV = Double.parseDouble(cv);
        return bathService.updateCv(id, CV);
    }

    @ResponseBody
    @RequestMapping(value = "/getCv.json", method = RequestMethod.POST)
    public double getCv(String subId) {
        int id = Integer.parseInt(subId);
        return bathService.getCv(id);
    }

    @ResponseBody
    @RequestMapping(value = "/updateCoordinates.json", method = RequestMethod.POST)
    public int updateCoordinates(int subId, double cv, double ct) {//添加坐标点
        return bathService.insertCoordinate(subId, new Point(ct, cv));
    }

    @ResponseBody
    @RequestMapping(value = "/clearCoordinates.json", method = RequestMethod.POST)
    public int clearCoordinates(int subId) {
        return bathService.clearCoordinate(subId);
    }

    @ResponseBody
    @RequestMapping(value = "/getCoordinates.json", method = RequestMethod.POST)
    public Coordinates getCoordinates(int subId) {
        return bathService.getCoordinates(subId);
    }

    @ResponseBody
    @RequestMapping(value = "/getCoordinate.json", method = RequestMethod.POST)
    public Point getCoordinate(int subId) {
        return bathService.getCoordinate(subId);
    }

    @ResponseBody
    @RequestMapping(value = "/setBathStatus.json", method = RequestMethod.POST)
    public int setBathStatus(int subId, int status) {
        return bathService.setBathStatus(subId, status);
    }
    @ResponseBody
    @RequestMapping(value = "/changeBathStatus.json", method = RequestMethod.POST)
    public int changeBathStatus(int subId, int status) {///新方法
        return bathService.changeBathStatus(subId, status);
    }
    @ResponseBody
    @RequestMapping(value = "/getBathStatus.json", method = RequestMethod.POST)
    public int getBathStatus(@RequestBody Map map) {
        return bathService.getBathStatus((int) map.get("subId"));
    }
    @ResponseBody
    @RequestMapping(value = "/endBath.json", method = RequestMethod.POST)//结束运行
    public int endBath(@RequestBody Map map) {
        if (map.containsKey("userId")){//说明涉及到记录保存
            int subId=(int) map.get("subId");
            int userId = (int) map.get("userId");
            //userService.createDeviceHistory(userId,subId);
            mqttService.closeDevice(subId);
        }
        return 0;
    }
    @ResponseBody
    @RequestMapping(value = "/openBath.json", method = RequestMethod.POST)//结束运行
    public int openBath(@RequestBody Map map) {
        //if (map.containsKey("userId")){//说明涉及到记录保存
            int subId=(int) map.get("subId");
            //int userId = (int) map.get("userId");
            //userService.createDeviceHistory(userId,subId);
            mqttService.openDevice(subId);
        //}
        return 0;
    }
    @ResponseBody
    @RequestMapping(value = "/updateBath.json", method = RequestMethod.POST)
    public boolean updateBath(@RequestBody Map map) {
        System.out.println(map.get("points")+"   "+map.get("status")+"    "+map.get("subId")+"      "+map.get("userId"));
        String point = (String)map.get("points");
        if (map.containsKey("startTime")){
            if (map.get("startTime")!=null){
                Date time=new Date((long)map.get("startTime"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String startTime=sdf.format(time);
                map.replace("startTime",startTime);
            }
        }
      //  int status=-1;
       // if (map.containsKey("status")){
          //  status = (int) map.get("status");
       // }
        int subId=(int) map.get("subId");

       // if (status==1){//将设备切换到开机模式
             //打断将要发送的mqtt数据包
             //重新关闭打开设备
          //  mqttService.interruptDevice(subId);
       // }
        int userId=-1;
           if (map.get("userId")!=null){
               userId=(int)map.get("userId");

           }

        if (point != null&& !point.equals("")&&userId!=-1) {
            //String points = point.toString();
            //map.replace("points", points);
            //如果points不为空，即代表用户点击了运行按钮
            System.out.println("ss");
        }
        if (bathService.updateBath(map) == 1)//更新数据库
            return true;
        return false;
    }
    @ResponseBody
    @RequestMapping(value = "/runBath.json", method = RequestMethod.POST)
    public void runBath(@RequestBody Map map){
        int userId=-1;
        if (map.get("userId")!=null){
            userId=(int)map.get("userId");
        }
        bathService.setSVandST((double) map.get("st"),(double) map.get("sv"),(int) map.get("subId"));
        mqttService.runDevice((int) map.get("subId"),userId);
    }
    @ResponseBody
    @RequestMapping(value = "/getStartTimeLong.json", method = RequestMethod.POST)
    public Long getStartTimeLong(@RequestBody Map map) {
        Date date=bathService.getStartTime((int) map.get("subId"));
        if (date!=null){
            return  date.getTime();
        }else {
            return  -1l;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/getStartTime.json", method = RequestMethod.POST)
    public String  getStartTime(@RequestBody Map map) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        java.util.Date date=bathService.getStartTime((int) map.get("subId"));
        if (date==null){
            return null;
        }
        return sdf.format(date);
    }

    @ResponseBody
    @RequestMapping(value = "/getPoints.json", method = RequestMethod.POST)
    public String getPoints(@RequestBody Map map) {
        return bathService.getPoints((int) map.get("subId"));
    }

    @ResponseBody
    @RequestMapping(value = "/updatePoints.json", method = RequestMethod.POST)
    public boolean updatePoints(@RequestBody Map map) {
        String oldPoints = bathService.getPoints((int) map.get("subId"));
        List<Point> points = JSONObject.parseArray(oldPoints, Point.class);
        String point = (String) map.get("point");
        logger.info(oldPoints);
        logger.info(point);
        Point p = JSONObject.parseObject(point, Point.class);
        points.add(p);
        map.put("points", JSON.toJSON(points).toString());
        return bathService.updatePoints(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getBathInfo.json", method = RequestMethod.POST)
    public Bath getBathInfo(@RequestBody Map map) {
        int subId = (int) map.get("subId");
        return bathService.getBathInfo(subId);
    }
    @ResponseBody
    @RequestMapping(value = "/getBathSVAndST.json", method = RequestMethod.POST)
    public Map getBathSVAndST(@RequestBody Map map) {
        int subId = (int) map.get("subId");
        return bathService.getBathSVAndST(subId);
    }
}
