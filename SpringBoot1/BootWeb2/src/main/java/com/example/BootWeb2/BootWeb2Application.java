package com.example.BootWeb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.BootWeb2.user.User;

@SpringBootApplication
public class BootWeb2Application {

	public static void main(String[] args) {
		SpringApplication.run(BootWeb2Application.class, args);
		User u = new User() ;
	
	}

}
