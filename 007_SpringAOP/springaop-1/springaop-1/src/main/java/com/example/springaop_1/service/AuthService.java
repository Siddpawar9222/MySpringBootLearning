package com.example.springaop_1.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AuthService {

    public boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        String username = "user";
        String password = "user";

        System.out.println("Please Enter username : ");
        String inputUsername = scanner.nextLine();
        System.out.println("Please Enter password : ");
        String inputPassword = scanner.nextLine();

        return username.equalsIgnoreCase(inputUsername) && password.equalsIgnoreCase(inputPassword);

    }
}
