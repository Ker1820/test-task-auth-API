package com.authapi.authapi.controllers;

import com.authapi.authapi.databaseEntities.User;
import com.authapi.authapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserDetailService uds;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        User user = (User) uds.loadUserByUsername(username);
        if (user == null) {
            return "redirect:/login";
        }
        return "login";
    }
}
