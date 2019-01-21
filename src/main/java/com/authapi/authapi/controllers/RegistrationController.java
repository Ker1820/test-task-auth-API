package com.authapi.authapi.controllers;
/*Controller with user validation
 *Encoding password and save user to database*/

import com.authapi.authapi.databaseEntities.User;
import com.authapi.authapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("Error", "");
        model.addAttribute("user", new User());
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String intoDB(@Valid User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "registrationPage";
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("Error", "There is such user!");
            return "registrationPage";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "registrationPage";

    }
}
