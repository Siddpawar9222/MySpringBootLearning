package com.jwt.example.BootWeb3.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.BootWeb3.model.User;
import com.jwt.example.BootWeb3.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService ;
	 
	@GetMapping("/users")
     public List<User> Home() {
    	    return this.userService.getUser() ;
     } 
	
	@GetMapping("/current-user")
	public String currentUser(Principal principal) {
		return principal.getName() ;
	} 
}
