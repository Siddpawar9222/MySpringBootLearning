package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = "prototype")        
public class Alien {
	private int id;
	private String name;
	private String tech;
//	@Autowired
//	@Qualifier("laptop1Bean")
	private Laptop laptop;

	public Alien() {
		super();
		System.out.println("Object of Alien is created.....");
	}
	
//  //Constructor injection	
//	@Autowired
//	public Alien(@Qualifier("laptop2Bean") Laptop laptop) {
//		this.laptop= laptop ;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public Laptop getLaptop() {
		return laptop;
	}
    
    //setter injection
	@Autowired
	public void setLaptop(@Qualifier("laptop2Bean") Laptop laptop) {
		this.laptop = laptop;
	}

	public void show() {
		System.out.println("In the show.....");
		laptop.compile();
		System.out.println(laptop.getId() + " " + laptop.getBrand());
	}

}
