package com.example.spring_caching_2.controller;
import com.example.spring_caching_2.dto.EmployeeRequest;
import com.example.spring_caching_2.exception.EmployeeNotFoundException;
import com.example.spring_caching_2.exception.EmployeesNotPresent;
import com.example.spring_caching_2.model.Employee;
import com.example.spring_caching_2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService ;

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getEmployees() throws EmployeesNotPresent {
       return ResponseEntity.ok(employeeService.getAllEmployees()) ;
    }

    @GetMapping("/getEmployeeById/{id}")
    public  ResponseEntity<Employee>getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployee(id)) ;
    }

    @PutMapping("/updateEmployeeById/{id}")
    public ResponseEntity<EmployeeRequest> updateEmployee(@PathVariable Integer id,@RequestBody EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        EmployeeRequest updatedEmployee = employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id)  {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id),HttpStatus.OK);
    }

}
