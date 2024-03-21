package com.example.A_SpringCore.di;

import org.springframework.stereotype.Component;

@Component
public class Cow implements Animal {

    public void makeNoise() {
        System.out.println("lowing..");
    }
}