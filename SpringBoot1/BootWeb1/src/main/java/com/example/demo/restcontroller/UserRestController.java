package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	public UserService userService;

	@GetMapping("/users")
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.getAllUser();
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable(value = "id") int id) {
		return userService.getUserById(id) ;
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		 userService.saveUser(user);
		 return user ;
	}
	
//	@PutMapping("/user/{id}")
//	public User editUser(@RequestBody User user , @PathVariable(value = "id") int id ) {
//		userService.updateUser(id,user);
//		return user ;
//	}
	
	@PutMapping("/user")
	public User editUser(@RequestBody User user) {
		userService.updateUser(user);
		return user ;
	}
	
	
	@PatchMapping("/user")
	public User modifyUser(@RequestBody User user) {
		userService.updateUser(user);
		return user ;
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable(value = "id") int id ) {
		userService.deleteUserById(id) ;
		return "User Deleted ";
	}
	
}
