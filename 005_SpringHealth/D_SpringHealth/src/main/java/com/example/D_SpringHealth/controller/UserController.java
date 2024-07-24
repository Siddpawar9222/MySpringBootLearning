package com.example.D_SpringHealth.controller;

import com.example.D_SpringHealth.dto.EmployeeRequest;
import com.example.D_SpringHealth.exception.EmployeeNotFoundException;
import com.example.D_SpringHealth.exception.EmployeesNotPresent;
import com.example.D_SpringHealth.model.Employee;
import com.example.D_SpringHealth.service.EmployeeService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static  final Logger LOGGER = LoggerFactory.getLogger(UserController.class) ;

    @Autowired
    private EmployeeService employeeService ;

    @PostMapping("/signup")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @Operation(
            tags = "GET Employee",
            description = "Get all Employee",
            responses = {
                    @ApiResponse(
                            description = "Success",
                   responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Data not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() throws EmployeesNotPresent {
        LOGGER.info("Employee Info Logging is Enabled...... Using devtools ");
        LOGGER.debug("Employee Info Dubbing is Enabled......");
       return ResponseEntity.ok(employeeService.getAllEmployees()) ;
    }

    @GetMapping("/employee/{id}")
    public  ResponseEntity<Employee>getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployee(id)) ;
    }

    @Hidden
    @GetMapping("/emp-age/{age}")
    public ResponseEntity<List<Employee>> findEmmployeeByAge(@PathVariable int age){
       return ResponseEntity.ok(employeeService.getEmployeeByAge(age));
    }

    @Hidden
    //employee email start with j
    @GetMapping("/emp-email/{email}")
    public ResponseEntity<List<Employee>> findEmmployeeByEmail(@PathVariable String email){
       return ResponseEntity.ok(employeeService.getEmployeeByEmail(email+"%"));
    }
}
