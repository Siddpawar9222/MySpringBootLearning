package com.security.springsecurity2.controller;

import com.security.springsecurity2.dto.UserDto;
import com.security.springsecurity2.exception.BadRequestException;
import com.security.springsecurity2.exception.ResourceUnavailableException;
import com.security.springsecurity2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService ;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws BadRequestException, ResourceUnavailableException {
        return authService.registerUser(userDto);
    }
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody UserDto userDto) {
       return  authService.authenticateAndGetToken(userDto) ;
    }
}
