package com.authapi.authapi.controllers;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableWebSecurity
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
