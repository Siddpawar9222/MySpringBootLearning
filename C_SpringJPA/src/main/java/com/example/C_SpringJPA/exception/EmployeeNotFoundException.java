package com.example.C_SpringJPA.exception;

public class EmployeeNotFoundException extends  Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
