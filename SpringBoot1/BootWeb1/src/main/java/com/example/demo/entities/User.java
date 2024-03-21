package com.example.demo.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userinfo")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id ;
   private String name ;
   private String city ;
   
   
public User() {
	super();
}



public User(String name, String city) {
	super();
	this.name = name;
	this.city = city;
}



public User(int id, String name, String city) {
	super();
	this.id = id;
	this.name = name;
	this.city = city;
}

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

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", city=" + city + "]";
}
   
}
