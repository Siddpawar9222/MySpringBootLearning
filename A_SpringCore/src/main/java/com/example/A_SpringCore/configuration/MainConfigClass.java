package com.example.A_SpringCore.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainConfigClass {
    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(MainConfigClass.class, args);

        AlienConfig alienConfig =  context.getBean(AlienConfig.class) ;
        System.out.println(alienConfig.getAlien());
        System.out.println(alienConfig.getAlienWithInfo());
        System.out.println(alienConfig.getAlien1());

        DBConfig dbConfig =  context.getBean(DBConfig.class) ;
        dbConfig.dataSourceProps();
    }
}
