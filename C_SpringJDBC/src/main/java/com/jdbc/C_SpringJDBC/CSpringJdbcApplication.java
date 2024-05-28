package com.jdbc.C_SpringJDBC;

import com.jdbc.C_SpringJDBC.model.Employee;
import com.jdbc.C_SpringJDBC.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.List;


@SpringBootApplication
public class CSpringJdbcApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger( CSpringJdbcApplication.class);

	public static void main(String[] args) {

		LOGGER.info("main method has started");

		ApplicationContext applicationContext = SpringApplication.run(CSpringJdbcApplication.class, args);

		EmployeeRepository employeeRepository = applicationContext.getBean(EmployeeRepository.class);

		Employee e1 = new Employee(1, "John Doe", "john.doe@example.com", "1234567890", "Male", 30, "American");
		Employee e2 = new Employee(2, "Jane Smith", "jane.smith@example.com", "9876543210", "Female", 25, "Canadian");
		Employee e3 = new Employee(3, "Anna Tyler", "anna.tyler@example.com", "5555555555", "Female", 20, "British");
		Employee e4 = new Employee(4, "Mark Brown", "mark.brown@example.com", "4444444444", "Male", 28, "Australian");

		List<Employee> employeeList = List.of(e1,e2,e3,e4);
		for (Employee e:employeeList){
			employeeRepository.save(e);
		}

		LOGGER.info("main method has ended");
	}
}
