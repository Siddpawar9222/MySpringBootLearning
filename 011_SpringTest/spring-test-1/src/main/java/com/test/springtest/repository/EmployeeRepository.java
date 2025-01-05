package com.test.springtest.repository;
import com.test.springtest.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    //jpql queries
    @Query("SELECT e FROM Employee e WHERE e.email LIKE :emailPattern")
    List<Employee> findEmployeeByEmail(@Param("emailPattern") String emailPattern);

    //Native queries
    @Query(value = "SELECT * FROM Employee WHERE age > :age", nativeQuery = true)
    List<Employee> findEmployeeByAge(@Param("age") int age);

}
