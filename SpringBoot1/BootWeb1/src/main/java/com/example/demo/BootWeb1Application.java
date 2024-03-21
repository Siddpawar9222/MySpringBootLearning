//Standalone application

package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

@SpringBootApplication()
public class BootWeb1Application {

	public static void main(String[] args) {
		
		/*
		ApplicationContext context = SpringApplication.run(BootWeb1Application.class, args);
		UserService userRepo = context.getBean(UserService.class);
        
        //save one entity
        
		User user = new User();
		user.setName("Bumrah");
		user.setCity("MP");
		userRepo.saveUser(user);
		*/
		
		ApplicationContext context = SpringApplication.run(BootWeb1Application.class, args);
		UserRepository userRepo = context.getBean(UserRepository.class);
        
		/*
		
		User user = new User();
		user.setName("Jadeja");
		user.setCity("UP");
		
		User user1 = new User();
		user1.setName("Ishan");
		user1.setCity("UP");
		
		//save bunch of entity
		
		List<User> users = List.of(user,user1) ;
		
		List<User> users1 = (List<User>) userRepo.saveAll(users) ;
		System.out.println(users1);
		
		*/
		
		
		/*
		
		//select all users(entities)
		
		 userRepo.findAll().forEach((element)->{System.out.println(element);});
		 
		 //select entity by id
         Optional<User> optional =userRepo.findById(155);
	     User user = optional.get();
	     
	     //First get entity and then edit and again save it(updating)
//	     user.setCity("Banglore");
//	     userRepo.save(user) ;
     
	     //Delete user
	     userRepo.delete(user);
	     
	     */
	     
		//Custom Queries
		
//	    List<User> users = userRepo.findByCity("UP") ;
//	    System.out.println(users);
		
		
		
//		List<User> users = userRepo.getUserByNameAndCity("rohit", "mumbai") ;
//		System.out.println(users);
//		
//		userRepo.getUsers().forEach((element)->System.out.println(element));
	     
		
		
	}

}