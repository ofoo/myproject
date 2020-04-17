package com.guoguo.ynz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
