package com.security.springsecurity1.controller;

import com.security.springsecurity1.dto.UserDto;
import com.security.springsecurity1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public")
public class PublicController {
    private final AuthService authService ;
    @GetMapping("/message")
    public String publicMessage(){
        return "This is Public URL" ;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        return authService.registerUser(userDto);
    }
}
