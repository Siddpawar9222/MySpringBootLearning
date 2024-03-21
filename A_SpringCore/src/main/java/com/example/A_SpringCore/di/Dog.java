package com.example.A_SpringCore.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Dog implements Animal {
    @Override
    public void makeNoise() {
        System.out.println("Barking......");
    }
}
