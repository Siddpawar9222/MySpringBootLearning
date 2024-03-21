package com.example.B_SpringMVC1.controller;


import com.example.B_SpringMVC1.model.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
@RequestMapping("/model-view")
public class MyController2 {

	@GetMapping("/")
	public String modelDisplay(){
		System.out.println("Calling .....");
		return "/WEB-INF/add.jsp";
	}


	@GetMapping("/register")
	public String doLogin(String name, String lang , Map<String , String> map) {
		System.out.println("Register calling.....");
		map.put("name",name ) ;
		map.put("lang",lang ) ;
		return "/WEB-INF/home.jsp";
	}

//	@GetMapping("/register1")
//	public String doLogin1(String name, String lang  , Model map) {
//		map.addAttribute("name",name ) ;
//		map.addAttribute("lang",lang ) ;
//		return "/WEB-INF/home.jsp";
//	}

	@GetMapping("/register1")
	public String doLogin1(Alien alien  , Model map) {
		map.addAttribute("obj", alien ) ;
		return "/WEB-INF/home.jsp";
	}
	
	@GetMapping("/register2")
	public String doLogin2(String name, String lang ,ModelMap map) {
		map.put("name",name ) ;
		map.put("lang",lang ) ;
		return "/WEB-INF/home.jsp";
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
        ModelAndView modelAndView = new ModelAndView("/WEB-INF/home.jsp");
        modelAndView.addObject("obj", alien);
        return modelAndView;
    }
	
}