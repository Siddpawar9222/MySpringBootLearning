package com.example.springaop_1.service;

import com.example.springaop_1.Exception.UniversalException;
import com.example.springaop_1.model.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();  // act as db

    public Employee addEmployee(Employee employee) throws UniversalException {
        for (String id : employees.keySet()) {
            Employee mappedEmp = employees.get(id);
            if (Objects.equals(mappedEmp.getName(), employee.getName())) {
                throw new UniversalException("Employee Already Exist in DB with name " + employee.getName());
            }
        }
        employees.put(this.generateUUID(), employee);
        return employee;
    }

    public Map<String, Employee> getAllEmployee() {

        return employees;
    }

    public Map<String, Employee> getEmployeeById(String id) throws UniversalException {
        if (!employees.containsKey(id)) {
            throw new UniversalException("Employee not found for id " + id);
        }
        return Map.of(id, employees.get(id));
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
