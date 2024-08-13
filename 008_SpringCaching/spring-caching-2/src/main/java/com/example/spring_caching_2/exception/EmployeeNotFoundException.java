package com.example.spring_caching_2.exception;

public class EmployeeNotFoundException extends  Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
