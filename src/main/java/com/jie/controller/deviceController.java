package com.jie.controller;

import com.alibaba.fastjson.JSONObject;
import com.jie.bean.Bath;
import com.jie.bean.Coordinates;
import com.jie.service.bathService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/deviceController")
public class deviceController {//设备控制
    @Autowired
    com.jie.service.bathService bathService=null;
    Logger logger=Logger.getLogger(deviceController.class);
    @ResponseBody
    @RequestMapping(value = "/getDevice.json", method = RequestMethod.POST)
    public JSONObject getDevice(@RequestBody Map map){
        JSONObject jsonObject=new JSONObject();
        /*
            根据deviceId返回具体类型
         */
        Integer deviceId=(Integer)map.get("deviceId");
        Integer subId=(Integer)map.get("subId");
        switch (deviceId){
            case 00001:{//水浴锅
                Bath bath=bathService.getBath(subId);
                jsonObject.put("device",bath);
                  break;
            }
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/settingBath.json", method = RequestMethod.POST)
    public JSONObject updateDevice(@RequestBody Map map){//设定设备
         /*
           需要做的工作
             更新CV sv st ct 坐标点
             坐标点
          */
         System.out.println(map.get("cv"));
          int subId=(int)map.get("subId");
          Bath bath=bathService.getBath(subId);//获取设备
          double sv=Double.valueOf(map.get("sv").toString());
          double st=Double.valueOf(map.get("st").toString());
          bath.setSt(st);
          bath.setSv(sv);
          //重置坐标系
          bath.setCoordinates(null);
        JSONObject object=new JSONObject();
        int row=bathService.settingBath(bath);
        object.put("status",row);//返回给android影响的行数
        logger.info(bath.toString());
        return object;
    }
    @ResponseBody
    @RequestMapping(value = "/updateCv.json", method = RequestMethod.POST)
    public int updateCv(String subId,String cv){
           int id=Integer.parseInt(subId);
           double CV=Double.parseDouble(cv);
           return bathService.updateCv(id,CV);
    }
    @ResponseBody
    @RequestMapping(value = "/getCv.json", method = RequestMethod.POST)
    public double getCv(String subId){
        int id=Integer.parseInt(subId);
        return bathService.getCv(id);
    }
    @ResponseBody
    @RequestMapping(value = "/updateCoordinates.json", method = RequestMethod.POST)
    public int updateCoordinates(int subId, double cv,double ct){//添加坐标点
       return bathService.insertCoordinate(subId,new Coordinates.Point(ct,cv));
    }
    @ResponseBody
    @RequestMapping(value = "/clearCoordinates.json", method = RequestMethod.POST)
    public int clearCoordinates(int subId){
       return  bathService.clearCoordinate(subId);
    }
    @ResponseBody
    @RequestMapping(value = "/getCoordinates.json", method = RequestMethod.POST)
    public Coordinates getCoordinates(int subId){
         return  bathService.getCoordinates(subId);
    }
    @ResponseBody
    @RequestMapping(value = "/getCoordinate.json", method = RequestMethod.POST)
    public Coordinates.Point getCoordinate(int subId){
        return  bathService.getCoordinate(subId);
    }
    @ResponseBody
    @RequestMapping(value = "/setBathStatus.json", method = RequestMethod.POST)
    public int setBathStatus(int subId,int status){
        return  bathService.setBathStatus(subId,status);
    }
    @ResponseBody
    @RequestMapping(value = "/getBathStatus.json", method = RequestMethod.POST)
    public int getBathStatus(int subId){
        return  bathService.getBathStatus(subId);
    }
}
