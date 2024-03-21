package com.start.Demo;

import org.springframework.stereotype.Component;

@Component
public class Tyre {
 private String brand ;    //Dependency
 
 

public Tyre(String brand) {
	super();
	this.brand = brand;
}

public String getBrand() {
	return brand;
}

public void setBrand(String brand) {
	this.brand = brand;
}

@Override
public String toString() {
	return "Working Fine";
}

//@Override
//public String toString() {
//	return "Tyre [brand=" + brand + "]";
//}


  
}
