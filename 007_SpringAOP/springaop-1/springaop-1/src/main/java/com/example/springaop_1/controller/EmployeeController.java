package com.example.springaop_1.controller;

import com.example.springaop_1.Exception.UniversalException;
import com.example.springaop_1.model.Employee;
import com.example.springaop_1.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService ;

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee , HttpServletRequest request) throws UniversalException {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<Map<String,Employee>> getAllEmployees(HttpServletRequest request){
         return new ResponseEntity<>(employeeService.getAllEmployee(),HttpStatus.OK);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Map<String,Employee>> getEmployeeById(@PathVariable("id") String id, HttpServletRequest request) throws UniversalException {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

}
