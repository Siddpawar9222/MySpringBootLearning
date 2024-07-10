package com.example.SpringEncryptionDecryption.service;


import com.example.SpringEncryptionDecryption.exception.EmployeeNotFoundException;
import com.example.SpringEncryptionDecryption.exception.EmployeesNotPresent;
import com.example.SpringEncryptionDecryption.model.Employee;
import com.example.SpringEncryptionDecryption.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository ;

    @Transactional
    public Employee saveEmployee(Employee employee){
        Employee savemployee = new Employee();
        savemployee.setName(employee.getName());
        savemployee.setAge(employee.getAge());
        savemployee.setEmail(employee.getEmail());
        savemployee.setPassword(employee.getPassword());
        savemployee.setGender(employee.getGender());
        savemployee.setNationality(employee.getNationality());
     return employeeRepository.save(savemployee) ;
    }

    @Transactional
    public List<Employee> getAllEmployees() throws EmployeesNotPresent {
        Iterator<Employee> employees  =  employeeRepository.findAll().iterator();
        List<Employee> employeeList = new ArrayList<>();
        while (employees.hasNext()) {
            employeeList.add(employees.next());
        }
        if (employeeList.isEmpty()) {
            throw new EmployeesNotPresent("Employee List is Empty");
        }
        return employeeList;
    }
    @Transactional
    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(() -> new EmployeeNotFoundException("User not found with ID " + id));
    }

    public List<Employee> getEmployeeByAge(int age){
        return employeeRepository.findEmployeeByAge(age);
    }
    public List<Employee> getEmployeeByEmail(String email){
        return employeeRepository.findEmployeeByEmail(email);
    }

}
