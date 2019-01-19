package com.authapi.authapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(){
        return "loginPage";
    }
//    @PostMapping("/login")
//    public String login(){
//        return "dataPage";
//    }
}
