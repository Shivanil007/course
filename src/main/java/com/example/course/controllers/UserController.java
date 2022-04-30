package com.example.course.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends ApplicationController {
    
    @GetMapping("/home")
    public String userHome() {
        return "userHome";
    }
}
