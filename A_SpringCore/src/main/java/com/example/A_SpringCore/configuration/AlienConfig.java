package com.example.A_SpringCore.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlienConfig {

     @Bean
     public Alien getAlien(){
         return  new Alien() ;
     }

     @Bean
     public Alien getAlienWithInfo(){
         System.out.println("How are You babay........");
        return  new Alien(12 ,"Siddhesh" ,"Java" , "sidd" ,"sidd") ;
     }


     @Bean
     public Alien1 getAlien1(){
         return  new Alien1() ;
     }
     //Here without bean it will give me null values
}
