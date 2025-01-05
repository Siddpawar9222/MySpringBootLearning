package com.test.springtest.controller;

import com.test.springtest.dto.EmployeeRequest;
import com.test.springtest.exception.EmployeeNotFoundException;
import com.test.springtest.exception.EmployeesNotPresent;
import com.test.springtest.model.Employee;
import com.test.springtest.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private EmployeeService employeeService ;

    @PostMapping("/signup")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() throws EmployeesNotPresent {
       return ResponseEntity.ok(employeeService.getAllEmployees()) ;
    }

    @GetMapping("/employee/{id}")
    public  ResponseEntity<Employee>getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployee(id)) ;
    }

    @GetMapping("/emp-age/{age}")
    public ResponseEntity<List<Employee>> findEmmployeeByAge(@PathVariable int age){
       return ResponseEntity.ok(employeeService.getEmployeeByAge(age));
    }

    //employee email start with j
    @GetMapping("/emp-email/{email}")
    public ResponseEntity<List<Employee>> findEmmployeeByEmail(@PathVariable String email){
       return ResponseEntity.ok(employeeService.getEmployeeByEmail(email+"%"));
    }
}
