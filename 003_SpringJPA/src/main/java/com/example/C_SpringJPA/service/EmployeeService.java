package com.example.C_SpringJPA.service;

import com.example.C_SpringJPA.dto.EmployeeRequest;
import com.example.C_SpringJPA.exception.EmployeeNotFoundException;
import com.example.C_SpringJPA.exception.EmployeesNotPresent;
import com.example.C_SpringJPA.model.Employee;
import com.example.C_SpringJPA.repository.EmployeeRepository;
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
    public Employee saveEmployee(EmployeeRequest employeeRequest){
        Employee employee = new Employee(0, employeeRequest.getName(), employeeRequest.getEmail(), employeeRequest.getMobileNo(), employeeRequest.getGender(), employeeRequest.getAge(), employeeRequest.getNationality());
     return employeeRepository.save(employee) ;
    }

    @Transactional
    public List<Employee> getAllEmployees() throws  EmployeesNotPresent{
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
