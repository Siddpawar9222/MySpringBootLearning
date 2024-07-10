package com.example.SpringEncryptionDecryption.controller;

import com.example.SpringEncryptionDecryption.exception.EmployeeNotFoundException;
import com.example.SpringEncryptionDecryption.exception.EmployeesNotPresent;
import com.example.SpringEncryptionDecryption.model.Employee;
import com.example.SpringEncryptionDecryption.service.EmployeeService;
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
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
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
