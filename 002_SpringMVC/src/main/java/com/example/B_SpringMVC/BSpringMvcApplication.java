package com.example.B_SpringMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.B_SpringMVC.controller"})
public class BSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BSpringMvcApplication.class, args);
	}

}
