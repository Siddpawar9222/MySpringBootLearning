package com.test.springtest.exception;

public class EmployeeNotFoundException extends  Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
