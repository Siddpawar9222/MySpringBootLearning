package com.example.SpringLogger_1.exception;

public class EmployeeNotFoundException extends  Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
