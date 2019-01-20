package com.authapi.authapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataController {
    @GetMapping("/userdata")
    public String getData() {
        return "dataPage";
    }
}
