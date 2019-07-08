package com.jie.controller.advice;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/advice")
public class MyAdviceController {
    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> testAdvice(Date date, @NumberFormat(pattern="##,###,00") BigDecimal amount, Model model){
        Map<String,Object> map=new HashMap<>();
        map.put("project_name",model.asMap().get("project"));
       // map.put("date",DateUtils.format(date,"yyyy-MM-dd"));
        map.put("date",date.toString());
        map.put("amount",amount);
        return map;
    }
}
