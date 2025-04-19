package com.example.springhealth.exception;

public class EmployeeNotFoundException extends  Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
