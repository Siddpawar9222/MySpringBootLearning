package com.example.BootWeb2.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.BootWeb2.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>  { 
	
      Optional<User> findByMail(String email ) ;
}
