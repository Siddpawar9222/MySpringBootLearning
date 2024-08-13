package com.example.spring_caching_2.service;

import com.example.spring_caching_2.dto.EmployeeRequest;
import com.example.spring_caching_2.exception.EmployeeNotFoundException;
import com.example.spring_caching_2.exception.EmployeesNotPresent;
import com.example.spring_caching_2.model.Employee;
import com.example.spring_caching_2.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
@Slf4j
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository ;

    @Transactional
    public Employee saveEmployee(EmployeeRequest employeeRequest){
        Employee employee = new Employee(0, employeeRequest.getName(), employeeRequest.getEmail(), employeeRequest.getMobileNo(), employeeRequest.getGender(), employeeRequest.getAge(), employeeRequest.getNationality());
     return employeeRepository.save(employee) ;
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
    @Cacheable(cacheNames = "employees",key = "#id")
    public Employee getEmployee(int id) throws EmployeeNotFoundException {
        log.info("getEmployee service has started ::: " + new Date());
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee = employeeOptional.orElseThrow(() -> new EmployeeNotFoundException("User not found with ID " + id));
        log.info("getEmployee service has Ended ::: " + new Date());
        return employee;
    }

    @Transactional
    @CachePut(cacheNames = "employees",key = "#id")
    public EmployeeRequest updateEmployee(int id, EmployeeRequest employeeRequest) throws EmployeeNotFoundException {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
            log.info("Employee not found with ID {}" , id);
            throw new EmployeeNotFoundException("User not found with ID " + id);
        }

        Employee employee = optionalEmployee.get();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setMobileNo(employeeRequest.getMobileNo());
        employee.setGender(employeeRequest.getGender());
        employee.setAge(employeeRequest.getAge());
        employee.setNationality(employeeRequest.getNationality());

        employeeRepository.save(employee);

        return employeeRequest;
    }

    @Transactional
    @CacheEvict(cacheNames = "employees", key = "#id")
    public String deleteEmployeeById(int id){
          employeeRepository.deleteById(id);
          return "Employee delete successfully by id " + id ;
    }
}
