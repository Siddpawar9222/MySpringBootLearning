package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean("laptop1Bean")
     Laptop laptop1Bean() {
        return new Laptop();
    }

    @Bean("laptop2Bean")
     Laptop laptop2Bean() {
        return new Laptop(2, "Brand2");
    }
}

//If i comment bean code then it will work because  of singleton scope