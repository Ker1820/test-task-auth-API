package com.authapi.authapi.controllers;

import com.authapi.authapi.databaseEntities.User;
import com.authapi.authapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String intoDB(@RequestParam String firstname, @RequestParam String lastname,
                         @RequestParam String username, @RequestParam String password) {
        User user = new User(firstname, lastname, username, passwordEncoder.encode(password));
        userRepository.save(user);
        return "registrationPage";

    }
}
