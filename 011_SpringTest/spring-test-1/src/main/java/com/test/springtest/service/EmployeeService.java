package com.test.springtest.service;

import com.test.springtest.dto.EmployeeRequest;
import com.test.springtest.exception.EmployeeNotFoundException;
import com.test.springtest.exception.EmployeesNotPresent;
import com.test.springtest.model.Employee;
import com.test.springtest.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

//    @Autowired
//    private EmployeeRepository employeeRepository ;   // *

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(null, employeeRequest.getName(), employeeRequest.getEmail(), employeeRequest.getMobileNo(), employeeRequest.getGender(), employeeRequest.getAge(), employeeRequest.getNationality());
        return employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> getAllEmployees() throws EmployeesNotPresent {
        Iterator<Employee> employees = employeeRepository.findAll().iterator();
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
    public Employee getEmployee(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(() -> new EmployeeNotFoundException("User not found with ID " + id));
    }

    public List<Employee> getEmployeeByAge(int age) {
        return employeeRepository.findEmployeeByAge(age);
    }

    public List<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

}
/*
 // * : I faced difficulties while writing test case for this class when i had used  @Autowired.
  We should use contructor injection instead of @Autowired.
*/