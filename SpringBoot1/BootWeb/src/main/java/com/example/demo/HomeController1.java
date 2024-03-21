package com.example.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController1 {
    
	@GetMapping("/register")
	public String doLogin(String name, String lang , Map<String , String> map) {
		map.put("name",name ) ;
		map.put("lang",lang ) ;
		return "home";
	}
	@GetMapping("/register1")
	public String doLogin1(String name, String lang  , Model map) {
		map.addAttribute("name",name ) ;
		map.addAttribute("lang",lang ) ;
		return "home";
	}
	
	@GetMapping("/register2")
	public String doLogin2(String name, String lang ,ModelMap map) {
		map.put("name",name ) ;
		map.put("lang",lang ) ;
		return "home";
	}
    
//	@GetMapping("/register3")
//	public ModelAndView doLogin3(String name , String lang) {
//        ModelAndView modelAndView = new ModelAndView("home");
//        modelAndView.addObject("name", name);
//        modelAndView.addObject("lang", lang);
//        return modelAndView;
//   }
	
	@GetMapping("/register3")
	public ModelAndView doLogin3(Alien alien) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("obj", alien);
        return modelAndView;
   }
	
}
