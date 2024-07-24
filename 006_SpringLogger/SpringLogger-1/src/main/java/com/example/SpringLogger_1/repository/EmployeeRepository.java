package com.example.SpringLogger_1.repository;

import com.example.SpringLogger_1.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    //Native queries
    @Query(value = "SELECT * FROM Employee WHERE age > :age", nativeQuery = true)
    List<Employee> findEmployeeByAge(@Param("age") int age);

    //jpql queries
    @Query("SELECT e FROM Employee e WHERE e.email LIKE :emailPattern")
    List<Employee> findEmployeeByEmail(@Param("emailPattern") String emailPattern);

}
