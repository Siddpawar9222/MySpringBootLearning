//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.example.demo.entities.User;
//import com.example.demo.service.UserService;
//
//
//@Controller
//public class UserController {
//	
//	@Autowired
//    private UserService userService ;
//    
////	@RequestMapping("/pages/")
////	public String home() {
////		return "home.jsp" ;
////	}
//	
//	@RequestMapping("/adduser")
//	public String addUser(User user) {
//		userService.saveUser(user) ;
//		return "index.html" ;
//	}
//	
//	@RequestMapping("/getAllUser")
//	public String getAllUser(Model model) {
//	    List<User> users = userService.getAllUser();
//	    model.addAttribute("users", users);
//	    return "alluser";
//	}
//
//	
//	
//	
//	
//}
