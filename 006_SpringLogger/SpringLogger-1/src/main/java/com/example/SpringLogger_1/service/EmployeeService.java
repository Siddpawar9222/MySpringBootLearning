package com.example.SpringLogger_1.service;

import com.example.SpringLogger_1.dto.EmployeeRequest;
import com.example.SpringLogger_1.exception.EmployeeNotFoundException;
import com.example.SpringLogger_1.exception.EmployeesNotPresent;
import com.example.SpringLogger_1.model.Employee;
import com.example.SpringLogger_1.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;


@Service
@Slf4j
// name of field would be log to print logs
public class EmployeeService {


    //private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    
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

    public EmployeeRequest updateEmployee(int id, EmployeeRequest employeeRequest) throws EmployeeNotFoundException {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isEmpty()){
//            LOGGER.info("Employee not found with ID {}" , id);
//            LOGGER.warn("Employee not found with ID {}" , id);
//            LOGGER.error("Employee not found with ID {}" , id);
//
//            LOGGER.debug("Employee not found with ID {}" , id);
//            LOGGER.trace("Employee not found with ID {}" , id);

            log.info("Employee not found with ID {}" , id);
            log.warn("Employee not found with ID {}" , id);
            log.error("Employee not found with ID {}" , id);

            log.debug("Employee not found with ID {}" , id);
            log.trace("Employee not found with ID {}" , id);


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


    public EmployeeRequest partialUpdateEmployee(int id, Map<String, Object> updates) throws EmployeeNotFoundException {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isEmpty()) {
            log.info("Employee not found with ID {}", id);
            log.warn("Employee not found with ID {}", id);
            log.error("Employee not found with ID {}", id);

            log.debug("Employee not found with ID {}", id);
            log.trace("Employee not found with ID {}", id);

            throw new EmployeeNotFoundException("User not found with ID " + id);
        }

        Employee employee = optionalEmployee.get();

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Employee.class, key);
            if(field==null){
                log.error("Invalid field: {}", key);
                throw new IllegalArgumentException("Invalid field: " + key);
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, employee, value);
        });

        Employee savedEmployee = employeeRepository.save(employee);
        return  employeeToEmployeeRequest(savedEmployee);
    }


    // Mapper
    private EmployeeRequest employeeToEmployeeRequest(Employee employee) {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName(employee.getName());
        employeeRequest.setEmail(employee.getEmail());
        employeeRequest.setMobileNo(employee.getMobileNo());
        employeeRequest.setGender(employee.getGender());
        employeeRequest.setAge(employee.getAge());
        employeeRequest.setNationality(employee.getNationality());
        return employeeRequest;
    }
}
