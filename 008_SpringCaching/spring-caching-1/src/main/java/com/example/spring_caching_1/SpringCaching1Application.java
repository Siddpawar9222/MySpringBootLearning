package com.example.spring_caching_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCaching1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCaching1Application.class, args);
	}

}
