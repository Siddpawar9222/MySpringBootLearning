package com.example.B_SpringMVC.restcontroller;

import com.example.B_SpringMVC.model.Alien;
import com.example.B_SpringMVC.service.AlienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")      //define base url
public class MyRestController {

    @Autowired
    private AlienService alienService ;

    @RequestMapping("/welcome")
    public String getWelcome(){
        return  "Welcome Buddy" ;
    }

    @RequestMapping(value = "/alien",method = RequestMethod.GET ,produces = "application/json")
    public List<Alien> aliens(){
        return  alienService.getAliens() ;
    }


}
