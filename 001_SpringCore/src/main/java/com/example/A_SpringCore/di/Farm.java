package com.example.A_SpringCore.di;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Farm {

    //Field Injection(Not recommended)
    @Autowired
    @Qualifier("cow")
    private Animal animal1 ;

    private Animal animal2 ;

    //Setter Injection
    @Autowired
    public void setAnimal(Animal animal2){
        this.animal2=animal2;
    }

    //Constructor injection
    private final Animal animal3;

    public void getAnimalNoiseField(){
         animal1.makeNoise();
    }
    public void getAnimalNoiseSetter(){
         animal2.makeNoise();
    }
    public void getAnimalNoiseConst(){
         animal3.makeNoise();
    }


}

//Without @RequiredArgsConstructor contructor will look like

public class Farm{
    private final Animal animal1 ;
    private final Animal animal2 ;

    public Farm(Animal animal1,Animal animal2){
        this.animal1=animal1;
        this.animal2 = animal2
    }
}