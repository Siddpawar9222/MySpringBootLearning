package com.start.Demo.BeamConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
    public static void main(String[] args) {
		ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class)  ;
		
		Samsung s7 = factory.getBean(Samsung.class);
		s7.config();
		
		
		
		
	}
}
