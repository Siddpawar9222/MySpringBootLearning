package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;


	public List<User> getAllUser() {
		return (List<User>) userRepo.findAll();
	}
	
	public User getUserById(int id) {
		Optional<User> optional = userRepo.findById(id);
		return optional.get();
	}

	public void saveUser(User user) {
		userRepo.save(user);
	}

	
//	public User updateUser(int id , User user) {
//	  User userData = getUserById(id) ;
//	  userData.setName(user.getName());
//	  userData.setCity(user.getCity());
//	  return userRepo.save(userData) ;
//	}

	public void updateUser(User user) {
         userRepo.save(user);
	}

	public void deleteUserById(int id) {
		userRepo.deleteById(id);
}

}
