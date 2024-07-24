package com.example.B_SpringMVC.service;

import com.example.B_SpringMVC.model.Alien;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlienService {
    List<Alien> list =  List.of(new Alien(1 ,"Ajit" ,"Java") , new Alien(2,"Pritam","pythan") ,new Alien(3,"Ramesh","AI")) ;

    List<Alien> alienList = new ArrayList<>(list) ;

    public List<Alien> getAliens(){
         return  alienList ;
    }

    public Alien getAlien(int id){
        return alienList.get(id-1) ;
    }

    public List<Alien> addAlien(Alien alien){
        alienList.add(alien) ;
        return getAliens() ;
    }
    public List<Alien> addAliens(List<Alien> aliens){
        alienList.addAll(aliens) ;
        return getAliens() ;
    }

    public List<Alien> updateAlien(Alien alien){
        alienList.set(alien.getId()-1 , alien) ;
        return getAliens() ;
    }

    public List<Alien> deleteAlien(int id){
        alienList.remove( id-1) ;
        return getAliens() ;
    }

    public List<Alien> deleteAliens(){
        alienList.clear(); ;
        return getAliens() ;
    }


}
