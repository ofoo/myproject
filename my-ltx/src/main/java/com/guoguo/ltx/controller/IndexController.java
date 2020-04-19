package com.guoguo.ltx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isAuthenticated());
        return "index";
    }
}
