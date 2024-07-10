package com.example.B_SpringMVC.restcontroller;

import com.example.B_SpringMVC.model.Alien;
import com.example.B_SpringMVC.service.AlienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest1")
public class MyRestController1 {
    @Autowired
    private AlienService alienService ;

    @GetMapping("aliens")
    public List<Alien> getAliens(){
        return  alienService.getAliens() ;
    }

    @GetMapping("/alien/{id}")
    public Alien getAlien(@PathVariable Integer id ){
        return alienService.getAlien(id) ;
    }
    //Here we must have to pass id here otherwise it will give us error(404)

    @GetMapping("/alien")
    public Alien getAlienByParam(@RequestParam("id") Integer id ){
        return alienService.getAlien(id) ;
    }
    //Here we must have to pass id here otherwise it will give us error(400)



    @PostMapping("/alien")
    public List<Alien> addAlien(@RequestBody Alien alien ){
        return alienService.addAlien(alien) ;
    }

    @PutMapping("/alien")
    public List<Alien> updateAlien(@RequestBody Alien alien ){
        return alienService.updateAlien(alien) ;
    }

    @DeleteMapping("/alien/{id}")
    public List<Alien> deleteAlien(@PathVariable Integer id ){
        return alienService.deleteAlien(id) ;
    }

    @DeleteMapping("/aliens")
    public List<Alien> deleteAliens(){
        return alienService.deleteAliens() ;
    }
}
