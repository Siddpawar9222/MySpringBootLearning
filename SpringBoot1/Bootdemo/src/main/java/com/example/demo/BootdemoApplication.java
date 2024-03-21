package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class BootdemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = 	SpringApplication.run(BootdemoApplication.class, args);
		
		Alien a = context.getBean(Alien.class);
		a.show();
		
		Alien a1 = context.getBean(Alien.class);
		a1.show();
		
		System.out.println(a.equals(a1));
		
		
		
	}

}

//By default scope of beam - singleton , it means
        //
        //Alien a = context.getBean(Alien.class);
        //a.show();
        //
        //Alien a1 = context.getBean(Alien.class);
        //a1.show();
        //
// If I don't write this by default spring container will create only one object(even here i created two objects)  '

// @Scope(value ="prototype")
// It will create the object by our will (here 2 objects) , if I dont write 
        
        
           //
           //Alien a1 = context.getBean(A   //
           //Alien a = context.getBean(Alien.class);
           //a.show();lien.class);
           //a1.show();
           //
    //It will create 0 objects