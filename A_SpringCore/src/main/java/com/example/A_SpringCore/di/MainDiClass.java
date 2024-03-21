package com.example.A_SpringCore.di;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainDiClass {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainDiClass.class, args);
        Farm bean = context.getBean(Farm.class);
        bean.getAnimalNoiseField();
        bean.getAnimalNoiseSetter();
        bean.getAnimalNoiseConst();

    }
}
