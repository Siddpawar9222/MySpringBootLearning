package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//@Controller
//public class HomeController {
//	
//	/*
//	@RequestMapping("/home")
//    public String Home(HttpServletRequest req , HttpSession session) {
//		String name = req.getParameter("name") ;
//		System.out.println(name);
//		session.setAttribute("name", name) ;
//    	    return "home" ;
//    }
//    
//	
//
//	@RequestMapping("/home")
//    public ModelAndView Home(@RequestParam("myName")String name) {
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("name" ,name) ;
//		mv.setViewName("home") ;
//		return mv ;
//}
//	*/
//	
////Object Model 
//	
//	@RequestMapping("/home")
//    public ModelAndView Home(Alien alien) {
//		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("obj" , alien ) ;
//		mv.setViewName("home") ;
//		return mv ;
//}
//	
//	
//}
//We can write servlet Interface in spring too
// Here spring gave use  HttpSession session object

//*******Then What is use of spring Here*******//
//Instant of HTTP session spring gives us ModelAndView


//Object Model 
//param writing(url ewriting : http://localhost:8080/home?id=10&name=siddhesh&lang=java)