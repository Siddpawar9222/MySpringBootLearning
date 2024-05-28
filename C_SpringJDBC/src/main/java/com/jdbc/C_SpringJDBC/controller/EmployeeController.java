package com.jdbc.C_SpringJDBC.controller;

import com.jdbc.C_SpringJDBC.model.Employee;
import com.jdbc.C_SpringJDBC.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public int save(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PostMapping("/saveinbatch")
    public int[] saveInBatch(@RequestBody List<Employee> employees) {
        return employeeRepository.batchInsert(employees);
    }


    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeRepository.findById(id);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @PutMapping
    public int update(@RequestBody Employee employee) {
        return employeeRepository.updateByNamedParameter(employee);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable int id) {
        return employeeRepository.deleteById(id);
    }
}

