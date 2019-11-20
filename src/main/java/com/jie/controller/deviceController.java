package com.jie.controller;

import com.jie.service.deviceService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/deviceController")
public class deviceController {
    private final static int BATH=1;
    @Autowired
    deviceService deviceService = null;
    Logger logger = Logger.getLogger(deviceController.class);
    @ResponseBody
    @RequestMapping(value = "/getLinkGuideInfo.json", method = RequestMethod.POST)
    public Map getLinkGuideInfo(@RequestBody Map map){
        int deviceId=(int)map.get("deviceId");
        return deviceService.getLinkGuideInfo(deviceId);
    }
    @ResponseBody
    @RequestMapping(value = "/updateDevice.json", method = RequestMethod.POST)
    public boolean updateDevice(@RequestBody Map map){
       return deviceService.updateDevice(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getDeviceName.json", method = RequestMethod.POST)
    public String getDeviceName(@RequestBody Map map){
        int deviceId=(int)map.get("deviceId");
        return deviceService.getDeviceName(deviceId);
    }
    @ResponseBody
    @RequestMapping(value = "/getSubNameById.json", method = RequestMethod.POST)
    public String getSubNameById(@RequestBody Map map){
        int deviceId=(int)map.get("deviceId");
        int subId=(int)map.get("subId");
        deviceService.getSubNameById(deviceId,subId);
        return null;
        //return deviceService.getDeviceName(deviceId);
    }
    @ResponseBody
    @RequestMapping(value = "/getManageUsersActivityInfo.json", method = RequestMethod.POST)
    public HashMap getManageUsersActivityInfo(@RequestBody Map map){
        return deviceService.getManageUsersActivityInfo(map);
        //return deviceService.getDeviceName(deviceId);
    }
    @ResponseBody
    @RequestMapping(value = "/getDeviceIdBySubId.json", method = RequestMethod.POST)
    public Integer getDeviceIdBySubId(@RequestBody Map map){
        int subId=(int)map.get("subId");
        return deviceService.getDeviceIdBySubId(subId);
    }

}
