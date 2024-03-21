// This is Spring Core Notes


package com.start.Demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
	public static void main(String[] args) {
		/*
		 * 
		 *  Car obj = new Car();        // Dependencies obj.drive(); //same for bike
		 * 
		 *  Vehicle obj1 = new Car() ;  //Reduce dependency(try to make loosely coupling) to  obj1.drive(); //same for bike
		 * 
		 * //Why even 1 dependency
		 * 
		 */
		
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/java/com/start/Demo/spring.xml");
//      Vehicle obj =(Vehicle) context.getBean("vehicle") ; 

		// Annotation based configuration
		
//		 Vehicle obj =(Vehicle) context.getBean("bike") ;  //  lowercase 
//		 obj.drive();

		
		// Dependency Injection (DI) 
//		Tyre t = (Tyre) context.getBean("tyre");
//		System.out.println(t.getBrand());

	  
		//Autowired
		
		Car objc  = (Car) context.getBean("car") ;
		objc.drive();


	}
}