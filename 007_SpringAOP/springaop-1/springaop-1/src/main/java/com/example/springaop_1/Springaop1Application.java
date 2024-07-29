package com.example.springaop_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Springaop1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springaop1Application.class, args);
    }

}
