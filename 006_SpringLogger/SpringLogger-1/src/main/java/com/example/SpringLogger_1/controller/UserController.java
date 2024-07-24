package com.example.SpringLogger_1.controller;

import com.example.SpringLogger_1.dto.EmployeeRequest;
import com.example.SpringLogger_1.exception.EmployeeNotFoundException;
import com.example.SpringLogger_1.exception.EmployeesNotPresent;
import com.example.SpringLogger_1.model.Employee;
import com.example.SpringLogger_1.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/upadate/{id}")
    public ResponseEntity<EmployeeRequest> updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        EmployeeRequest updatedEmployee = employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PatchMapping ("/updatepartial/{id}")
    public ResponseEntity<EmployeeRequest> updateEmployeePartial(@PathVariable Integer id, @RequestBody Map<String, Object> fields) throws EmployeeNotFoundException {
        EmployeeRequest updatedEmployee = employeeService.partialUpdateEmployee(id, fields);
        return ResponseEntity.ok(updatedEmployee);
    }

}
