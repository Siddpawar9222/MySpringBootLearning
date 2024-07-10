package com.example.B_SpringMVC1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieWebController {
    @GetMapping("/")
    public String welcomePage(){
        return "html/index.html";
    }
}
