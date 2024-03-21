package com.example.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Integer>  { 
	public List<User> findByCity(String city);
	
	
	@Query("SELECT u FROM User u WHERE u.name = :n AND u.city = :c")
	public List<User> getUserByNameAndCity(@Param("n") String name, @Param("c") String city);

	@Query(value = "select * from  userinfo" , nativeQuery= true)
	public List<User> getUsers();
}
