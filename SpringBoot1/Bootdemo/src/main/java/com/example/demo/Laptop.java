package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = "prototype")
public class Laptop {
	private int id;
	private String brand;
	
	

	public Laptop() {
		super();
		System.out.println("Object of Laptop is created");
	}

	public Laptop(int id, String brand) {
		super();
		this.id = id;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", brand=" + brand + "]";
	}

	public void compile() {
		System.out.println("compiled......");
	}
}
