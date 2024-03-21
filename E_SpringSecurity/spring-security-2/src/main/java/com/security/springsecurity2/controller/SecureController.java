package com.security.springsecurity2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class SecureController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security" ;
    }

    @GetMapping("/admin")
    public String welcomeAdmin(){
        return "Welcome to Spring Security ! ADMIN" ;
    }

    @GetMapping("/user")
    public String welcomeUser(){
        return "Welcome to Spring Security ! User" ;
    }

}
