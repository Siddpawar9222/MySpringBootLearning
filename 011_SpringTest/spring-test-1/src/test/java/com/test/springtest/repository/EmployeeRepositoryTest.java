package com.test.springtest.repository;

import com.test.springtest.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class EmployeeRepositoryTest {

    // given
    // when
    // then
    @Autowired
    private EmployeeRepository employeeRepository;

    Employee employee ;

    @BeforeEach
    void setUp() {
       employee = new Employee(null ,"Siddhesh", "siddhesh@123", "1234567890", "Male", 22, "Indian");
       employeeRepository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employee=null;
        employeeRepository.deleteAll();
    }

    @Disabled
    @Test
    public void testSomething(){
          assertEquals(10,5+4);
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "1,2,3",
                    "2,3,5",
                    "3,4,7",
                    "4,5,9",
                    "5,6,11",
                    "6,7,13",
                    "7,8,15",
                    "8,9,17",
                    "9,10,19",
                    "10,11,21"
            }
    )  // Here you can load csv file from application.properties file too
    public  void testSomething(int a, int b,int expected){
        assertEquals(expected, a+b);
    }


    @ParameterizedTest
    @ValueSource(
            strings = {"siddhesh@123","+++++++"}
    )
    public  void testSomething(String email){
         assertNotNull(employeeRepository.findEmployeeByEmail(email),"Failed for "+email);
    }


    // Test case : SUCCESS
    @Test
    void findEmployeeByEmailFound() {
        List<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail("siddhesh@123");
        assertThat(employeeByEmail.get(0).getId()).isEqualTo(employee.getId());
        assertThat(employeeByEmail.get(0).getEmail()).isEqualTo(employee.getEmail());
    }


    // Test case : FAILURE
    @Test
    void findEmployeeByEmailNotFound() {
        List<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail("123");
        assertThat(employeeByEmail.isEmpty()).isTrue();
    }
}
