package com.example.A_SpringCore.scope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainScopeClass {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainScopeClass.class, args);
//       Alien bean = context.getBean(Alien.class);
//       Alien bean1 = context.getBean(Alien.class);

    }
}
