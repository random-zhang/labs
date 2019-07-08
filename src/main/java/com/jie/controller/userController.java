package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jie.bean.User;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    UserService userService=null;
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(String id){
        return userService.getUser(id);
    }
    @ResponseBody
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response, Model model,@RequestBody Map map ){
        User user=new User();
        user.setUserPhone((String)map.get("userPhone"));
        user.setUserPassword((String)map.get("userPassword"));
        user=userService.login(user);
        response.setContentType("application/json");
        String jsonStr=null;
        JSONObject obj=new JSONObject();
        obj.put("user",user);
        if(user!=null){
            obj.put("msg","登录成功！");
            obj.put("status",1);
        }else{
            obj.put("status",0);
        }
        sendAsJson(response,obj);
    }
    @ResponseBody
    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response,@RequestBody Map map ){
        User user=new User();
        user.setUserPhone((String)map.get("userPhone"));
        user.setUserPassword((String)map.get("userPassword"));
        user.setUserPortrait((byte[])map.get("userPortriat"));
        user.setUserName((String)map.get("userName"));
        JSONObject obj=new JSONObject();
        int id=userService.register(user);
        obj.put("status",id);

        switch (id){
            case 100: {
                obj.put("msg","该手机号已注册！");
                break;
            }
            case 101:{
                obj.put("msg","注册失败！");
                break;
            }
            default:{
                obj.put("msg","注册成功！");
                break;
            }
        }
        sendAsJson(response,obj);
    }

    public void sendAsJson(HttpServletResponse response, JSONObject obj ){
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

}
