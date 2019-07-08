package com.wan.controller;

import com.wan.model.Employee;
import com.wan.service.EmployeeService;
import com.wan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/EmployeeController")
public class EmployeeController {
    //request.setAttr
    //request.gerRequestDispather()
    @Autowired
    EmployeeService  employeeService=null;
    @RequestMapping(value = "/getDeapartAndPostionInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object> getEmployees(HttpServletRequest request, HttpServletResponse response, @RequestBody Map map){
     //获取所有用户信息
        Map<String, Object> resultMap =  new HashMap<String,Object>();
        List<Employee>  employees=employeeService.getEmployees();
         resultMap.put("employees",employees);
         return  resultMap;
    }
}
