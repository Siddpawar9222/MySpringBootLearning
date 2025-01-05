package com.test.springtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springtest.advice.ApplicationExceptionHandler;
import com.test.springtest.dto.EmployeeRequest;
import com.test.springtest.exception.EmployeeNotFoundException;
import com.test.springtest.exception.EmployeesNotPresent;
import com.test.springtest.model.Employee;
import com.test.springtest.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private UserController userController;

    @InjectMocks
    private ApplicationExceptionHandler applicationExceptionHandler;

    private Employee employee1;
    private Employee employee2;
    private EmployeeRequest employeeRequest;

    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(applicationExceptionHandler)
                .build();

        employee1 = new Employee(1, "Siddhesh", "siddhesh@123", "1234567890", "Male", 22, "Indian");
        employee2 = new Employee(2, "Amit", "amit@123", "1234567890", "Male", 22, "Indian");
        employeeRequest = new EmployeeRequest("Siddhesh", "siddhesh@123", "1234567890", "Male", 22, "Indian");
        employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @AfterEach
    void tearDown() {
        employeeList.clear();
    }

    @Test
    void getEmployeeSuccessJsonPath() throws Exception {
        when(employeeService.getEmployee(1)).thenReturn(employee1);

        mockMvc.perform(get("/users/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Siddhesh"))
                .andExpect(jsonPath("$.email").value("siddhesh@123"))
                .andExpect(jsonPath("$.mobileNo").value("1234567890"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.nationality").value("Indian"))

        ;
    }
    @Test
    void getEmployeeSuccessMapper() throws Exception {
        when(employeeService.getEmployee(1)).thenReturn(employee1);

        mockMvc.perform(get("/users/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(employee1)));
    }


    @Test
    void saveEmployeeSuccess() throws Exception {
        when(employeeService.saveEmployee(employeeRequest)).thenReturn(employee1);

        mockMvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employeeRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Siddhesh"))
                .andExpect(jsonPath("$.email").value("siddhesh@123"))
                .andExpect(jsonPath("$.mobileNo").value("1234567890"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.nationality").value("Indian"));
    }

    @Test
    void getEmployeesSuccess() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(employeeList);

        mockMvc.perform(get("/users/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Siddhesh"));
    }

    @Test
    void getEmployeeSuccess() throws Exception {
        when(employeeService.getEmployee(1)).thenReturn(employee1);

        mockMvc.perform(get("/users/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Siddhesh"));
    }


    @Test
    void findEmployeeByAgeSuccess() throws Exception {
        when(employeeService.getEmployeeByAge(22)).thenReturn(employeeList);

        mockMvc.perform(get("/users/emp-age/22")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].age").value(22));
    }

    @Test
    void findEmployeeByEmailSuccess() throws Exception {
        when(employeeService.getEmployeeByEmail("j%")).thenReturn(employeeList);

        mockMvc.perform(get("/users/emp-email/j")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("siddhesh@123"));
    }

    @Test
    void getEmployeesNotPresent() throws Exception {
        when(employeeService.getAllEmployees()).thenThrow(EmployeesNotPresent.class);

        mockMvc.perform(get("/users/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getEmployeeNotFound() throws Exception {
        when(employeeService.getEmployee(1)).thenThrow(EmployeeNotFoundException.class);

        mockMvc.perform(get("/users/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}