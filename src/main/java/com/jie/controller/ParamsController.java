package com.jie.controller;

import com.jie.bean.User;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
@RequestMapping("/params")
public class ParamsController {
    @Autowired
    UserService userService=null;
    @RequestMapping("/getUser/{id}")
    public ModelAndView pathVariable(@PathVariable("id") String id){
        User user=userService.getUser(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject(user);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}
