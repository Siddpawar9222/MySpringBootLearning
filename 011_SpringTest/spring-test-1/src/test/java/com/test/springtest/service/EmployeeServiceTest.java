package com.test.springtest.service;

import com.test.springtest.dto.EmployeeRequest;
import com.test.springtest.exception.EmployeeNotFoundException;
import com.test.springtest.exception.EmployeesNotPresent;
import com.test.springtest.model.Employee;
import com.test.springtest.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;


class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    AutoCloseable autoCloseable;

    Employee returnEmployee;
    EmployeeRequest employeeRequest;

    @BeforeEach
    void setUp() {
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        this.employeeService = new EmployeeService(employeeRepository);

        this.returnEmployee = new Employee(1, "Siddhesh", "siddhesh@123", "1234567890", "Male", 22, "Indian");
        this.employeeRequest = new EmployeeRequest("Siddhesh", "siddhesh@123", "1234567890", "Male", 22, "Indian");
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
        this.returnEmployee = null;
        this.employeeRequest = null;
    }

    @Test
    void saveEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(returnEmployee);
        Employee savedEmployee = employeeService.saveEmployee(employeeRequest);
        assertThat(savedEmployee).isEqualTo(returnEmployee);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }


    @Test
    void getAllEmployeesSuccess() throws EmployeesNotPresent {
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(returnEmployee));
        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertThat(allEmployees).isEqualTo(Collections.singletonList(returnEmployee));

        // Assertions to verify result
        assertThat(allEmployees).isNotNull();
        assertThat(allEmployees.size()).isEqualTo(1);
        assertThat(allEmployees).contains(returnEmployee);

        // Verify repository interaction
        verify(employeeRepository, times(1)).findAll();
    }


    @Test
    void getAllEmployeesFailure() {
        // Mock repository behavior to return an empty list as Iterable
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the service method and assert exception
        assertThatThrownBy(() -> employeeService.getAllEmployees())
                .isInstanceOf(EmployeesNotPresent.class)
                .hasMessage("Employee List is Empty");

        // Verify repository interaction
        verify(employeeRepository, times(1)).findAll();
    }


    @Test
    void getEmployee() throws EmployeeNotFoundException {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(returnEmployee));
        Employee employee = employeeService.getEmployee(1);
        assertThat(employee).isEqualTo(returnEmployee);
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    void deleteEmployeeById(){
        // Arrange
        Integer employeeId = 1;

        // Act
        employeeService.deleteEmployeeById(employeeId);

        // Assert
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

}