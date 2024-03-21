package com.jwt.example.BootWeb3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jwt.example.BootWeb3.model.User;

@Service
public class UserService {
	
       private List<User> list = new ArrayList<>() ;
       
       public UserService() {
    	      list.add(new User(UUID.randomUUID().toString(), "Ankit Tiwari", "ankit@gmail.com"));
    	      list.add(new User(UUID.randomUUID().toString(), "Pankaj Sharma", "pankaj@gmail.com"));
    	      list.add(new User(UUID.randomUUID().toString(), "Harshad", "har@gmail.com"));
       }
       
       public List<User> getUser(){
    	   return this.list ;
       }
       
}
