package com.example.springaop_1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Slf4j
public class AuthService {

    public boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        String username = "user";
        String password = "user";

        log.info("Please Enter username : ");
        String inputUsername = scanner.nextLine();
        log.info("Please Enter password : ");
        String inputPassword = scanner.nextLine();

        return username.equalsIgnoreCase(inputUsername) && password.equalsIgnoreCase(inputPassword);

    }
}
