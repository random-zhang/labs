package com.jie.controller;

import com.alibaba.fastjson.JSONObject;
import com.jie.bean.*;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    UserService userService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(String id) {
        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody Map map) {
        User user = new User();
        user.setUserPhone((String) map.get("userPhone"));
        user.setUserPassword((String) map.get("userPassword"));
        user = userService.login(user);
        response.setContentType("application/json");
        String jsonStr = null;
        JSONObject obj = new JSONObject();
        obj.put("user", user);
        if (user != null) {
            obj.put("msg", "登录成功！");
            obj.put("status", 1);
        } else {
            obj.put("status", 0);
        }
        sendAsJson(response, obj);
    }

    @ResponseBody
    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public JSONObject register(@RequestBody Map map) {
        User user = new User();
        user.setUserPhone((String) map.get("userPhone"));
        user.setUserPassword((String) map.get("userPassword"));
        //user.setUserPortrait((byte[])map.get("userPortriat"));
        user.setUserName((String) map.get("userName"));

        JSONObject obj = new JSONObject();
        int id = userService.register(user);
        obj.put("status", id);
        user.setUserId(id + "");
        switch (id) {
            case 100: {
                obj.put("msg", "该手机号已注册！");
                break;
            }
            case 101: {
                obj.put("msg", "注册失败！");
                break;
            }
            default: {
                obj.put("msg", "注册成功！");
                //返回注册的User
                obj.put("user", user);
                break;
            }
        }
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/isRegister.json", method = RequestMethod.POST)
    public JSONObject isRegister(@RequestBody Map map) {//判断是否注册
        String userPhone = (String) map.get("userPhone");
        JSONObject object = new JSONObject();
        if (userService.getUserByPhone(userPhone) == null) {//未被注册
            object.put("msg", "该手机号未注册！");
            object.put("status", 102);
        } else {
            object.put("msg", "该手机号已注册！");
            object.put("status", 100);
        }
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/getMyDevices.json", method = RequestMethod.POST)
    public List<myDevice> getMyDevices(@RequestBody Map map) {
        int userID = (int) map.get("userId");
        List<myDevice> myDevices = userService.getDevices(userID);
        return myDevices;

    }

    @ResponseBody
    @RequestMapping(value = "/bindDevice.json", method = RequestMethod.POST)//绑定设备
    public boolean bindDevice(@RequestBody Map map) {
        return userService.bindDevice(map);
    }
    @ResponseBody
    @RequestMapping(value = "/bindDeviceByQRCode.json", method = RequestMethod.POST)//绑定设备
    public boolean bindDeviceByQRCode(@RequestBody Map map) {
        return userService.bindDevice(map);
    }
    @ResponseBody
    @RequestMapping(value = "/UnbundlingDevice.json", method = RequestMethod.POST)
    public boolean UnbundlingDevice(@RequestBody Map map) {
        return userService.UnbundlingDevice(map);
    }

    @ResponseBody
    @RequestMapping(value = "/selectMyhistoryDevices.json", method = RequestMethod.POST)
    public List<myDeviceHistory> selectMyhistoryDevices(@RequestBody Map map) {
        return userService.selectMyhistoryDevices((int) map.get("userId"));
    }

    @ResponseBody
    @RequestMapping(value = "/selectMyhistoryDeviceNames.json", method = RequestMethod.POST)
    public List<Map> selectMyhistoryDeviceNames(@RequestBody Map map) {
        return userService.selectMyHistoryDeviceNameAndId((int) map.get("userId"));
    }

    @ResponseBody
    @RequestMapping(value = "/updateDevicePosition.json", method = RequestMethod.POST)
    public boolean updateDevicePosition(@RequestBody Map map) {
        int userId = (int) map.get("userId");
        int subId = (int) map.get("subId");
        return userService.updateDevicePosition(userId, subId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateMyDevice.json", method = RequestMethod.POST)
    public boolean updateMyDevice(@RequestBody Map map) {
        int userId = (int) map.get("userId");
        int subId = (int) map.get("subId");
        String alias = (String) map.get("alias");
        return userService.updateMyDevices(subId, userId, alias);
    }

    public void sendAsJson(HttpServletResponse response, JSONObject obj) {
        PrintWriter output;
        try {
            response.setCharacterEncoding("utf-8");
            output = response.getWriter();
            // dataByte = jsonStr.;
            output.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/insertDevicePoints.json", method = RequestMethod.POST)
    public void insertDevicePoints(@RequestBody Map map) {
        int deviceId = (int) map.get("deviceId");
        int userId = (int) map.get("userId");
        String points = (String) map.get("points");
        System.out.println(deviceId + "    " + userId + "        " + points);
        if (points != null && !points.equals("")) {
            userService.insertDevicePoints(deviceId, userId, points);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDevicePoints.json", method = RequestMethod.POST)
    public void deleteDevicePoints(@RequestBody Map map) {
        int deviceId = (int) map.get("deviceId");
        int userId = (int) map.get("userId");
        int points = (int) map.get("position");
        userService.deleteDevicePoints(deviceId, userId, points);
    }

    @ResponseBody
    @RequestMapping(value = "/selectDevicePoints.json", method = RequestMethod.POST)
    public List<String> selectDevicePoints(@RequestBody Map map) {//获取对应用户对应设备的所有points，存在返回该字符串，否则返回null
        int deviceId = (int) map.get("deviceId");
        int userId = (int) map.get("userId");
        return userService.selectDevicePoints(deviceId, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/selectDevicePositionAndPoints.json", method = RequestMethod.POST)
    public List<PositionAndPoints> selectDevicePositionAndPoints(@RequestBody Map map) {//获取对应用户对应设备的所有points，存在返回该字符串，否则返回null
        int deviceId = (int) map.get("deviceId");
        int userId = (int) map.get("userId");
        return userService.selectDevicePositionAndPoints(deviceId, userId);
    }

  /*  @ResponseBody
    @RequestMapping(value = "/isExistInDevicePoints.json", method = RequestMethod.POST)
    public boolean isExistInDevicePoints(@RequestBody Map map) {
        String pointsR = (String) map.get("points");
        if (pointsR == null || pointsR.equals(""))
            return false;
        List<String> pointsList = selectDevicePoints(map);
        if (pointsList != null) {
            if (pointsList.size() != 0) {
                for (String points : pointsList) {
                    if (points.equals(pointsR)) {//如果存在返回true
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    @ResponseBody
    @RequestMapping(value = "/getMyDeviceHistory.json", method = RequestMethod.POST)
    public ArrayList<HashMap> getMyDeviceHistory(@RequestBody Map map) {//获取用户的historyId
        int userId = (int) map.get("userId");
        return userService.getMyDevicesHistoryIdsBySub(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getSubNameById.json", method = RequestMethod.POST)
    public String getSubNameById(@RequestBody Map map) {//获取用户的historyId
        int userId = (int) map.get("userId");
        int subId = (int) map.get("subId");
        return userService.getSubNameById(userId, subId);
    }

    @ResponseBody
    @RequestMapping(value = "/getHistoryByHistoryId.json", method = RequestMethod.POST)
    public HashMap getHistoryByHistoryId(@RequestBody Map map) {//获取用户的historyId
        int userId = (int) map.get("userId");
        int historyId = (int) map.get("historyId");
        return userService.getHistoryByHistoryId(userId, historyId);
    }

    @ResponseBody
    @RequestMapping(value = "/getMyManageDevice.json", method = RequestMethod.POST)
    public HashMap getMyManageDevice(@RequestBody Map map) {//获取用户的historyId
        int userId = (int) map.get("userId");
        int subId = (int) map.get("subId");
        return userService.getMyManageDevice(userId, subId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateMyManageDevice.json", method = RequestMethod.POST)
    public int updateMyManageDevice(@RequestBody Map map) {//获取用户的historyId
        //int userId=(int)map.get("userId");
        // int subId=(int)map.get("subId");
        // String musers=(String)map.get("musers");
        //int maxCapacity=(int)map.get("maxCapacity");
        //String authorizedCode=(String)map.get("authorizedCode");

        return userService.updateMyManageDevice(map);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeviceFromMyDevices.json", method = RequestMethod.POST)
    public int deleteDeviceFromMyDevices(@RequestBody Map map) {//获取用户的historyId
        return userService.deleteDeviceFromMyDevices(map);
    }
    @ResponseBody
    @RequestMapping(value = "/getMyAlias.json", method = RequestMethod.POST)
    public List  getMyAlias(@RequestBody Map map) {//获取用户的historyId
        int userId=(int)map.get("userId");
        return userService. getMyAlias(userId);
    }
    @ResponseBody
    @RequestMapping(value = "/getMyAlia.json", method = RequestMethod.POST)
    public String  getMyAlia(@RequestBody Map map) {//获取用户的historyId
        int userId=(int)map.get("userId");
        int subId=(int)map.get("subId");
        return userService.getMyAlia(userId,subId);
    }
    @ResponseBody
    @RequestMapping(value = "/bindDeviceByAuthorizedCode.json", method = RequestMethod.POST)
    public String bindDeviceByAuthorizedCode(@RequestBody Map map){
        return userService.bindDeviceByAuthorizedCode(map);
    }
    @ResponseBody
    @RequestMapping(value = "/test.json", method = RequestMethod.POST)
    public void test(@RequestBody test t){

       /* ArrayList<Integer[]> b=(ArrayList<Integer[]>)map.get("b");
        int row=b.size();
        int column=b.get(0).length;
        int[][] a=new int[row][column];
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++)
            a[i][j]=((Integer[])b.get(i))[j];
        }*/
        System.out.println(t.getQ());
    }
    @ResponseBody
    @RequestMapping(value = "/getSubHistoryIds.json", method = RequestMethod.POST)
    public  Map    getSubHistoryIds(@RequestBody Map map) {
        int userId=(int)map.get("userId");
        int subId=(int)map.get("subId");
       return userService.getSubHistoryIds(userId,subId);
    }
}
