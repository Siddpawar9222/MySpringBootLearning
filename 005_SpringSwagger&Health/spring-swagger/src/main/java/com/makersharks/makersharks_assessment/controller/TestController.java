package com.makersharks.makersharks_assessment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
@Tag(name = "Test" ,description = "Test Application")
public class TestController {
    @GetMapping("/welcome")
    public String testApplication(){
        return "Welcome to Spring Boot Application" ;
    }
}
