package com.jie.controller.advice;

import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;

@ControllerAdvice(basePackages = {"com.jie.controller.advice"})
public class CommonControllerAdvice {
    //http参数处理
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }
    @ModelAttribute
    public void populateModel(Model model){
        model.addAttribute("project","chapter16");
    }
    @ExceptionHandler(Exception.class)
    public String exception(){
        return "exception";
    }
}
