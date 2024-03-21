package com.security.springsecurity2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public")
public class PublicController {
    @GetMapping("/message")
    public String publicMessage(){
        return "This is Public URL" ;
    }
}
