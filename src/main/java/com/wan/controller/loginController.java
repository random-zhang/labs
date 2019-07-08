package com.wan.controller;

import com.wan.service.LoginService;
import com.wan.dto.LoginDto;
import com.wan.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/LoginController")
public class loginController {
    @Autowired
    LoginService loginService=null;
    @RequestMapping(value = "/loginCheck.do",method = RequestMethod.POST)
    @ResponseBody
    protected Map<String, Object> loginCheck(HttpServletRequest request, HttpServletResponse response,@RequestBody Map map){
        /*在数据库中比对

         */
        Map<String, Object> resultMap =  new HashMap<String,Object>();
        String employeeId=(String)map.get("employeeId");
        String password=(String)map.get("password");
        try {
            LoginDto dto=loginService.getUser(employeeId,password);
            if(dto!=null){
                if(password.equals("123456")){
                       // response.sendRedirect(request.getContextPath()+ "/wanho/updatepassword.jsp");
                        resultMap.put("msg","密码过于简单");
                }else{
                    resultMap.put("msg","密码正确");
                    request.getSession().setAttribute("ISLOGIN",true);
                }
            }else{
                    resultMap.put("msg","密码错误");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    protected void loginToCms(HttpServletRequest request,HttpServletResponse response){
        try {
            response.sendRedirect(request.getContextPath()+ "/ControlServlet?method=countAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/updatePassword.do",method = RequestMethod.POST)
    @ResponseBody
    protected Map<String, Object>  updatePassword(HttpServletRequest request,HttpServletResponse response,@RequestBody Map map){//更新密码
        String employeeId=(String)map.get("employeeId");
        String password=(String)map.get("password");
        String oldpassword=(String)map.get("oldpassword");
        Map<String, Object> resultMap =  new HashMap<String,Object>();
        //先进行验证，看原密码是否正确
        try {
            LoginDto dto=loginService.getUser(employeeId,oldpassword);
            if(dto==null) {//原密码错误
                resultMap.put("msg","原密码错误");
            }else{
                int row=loginService.updatePassword(employeeId,password);
                if(row==1){//修改成功
                    resultMap.put("msg","修改成功");
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    protected void logout(HttpServletRequest request,HttpServletResponse response){
        try {
            response.sendRedirect(request.getContextPath()+ "/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
