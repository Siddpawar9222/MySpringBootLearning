package com.test.springtest.exception;

public class EmployeesNotPresent extends  Exception {
    public EmployeesNotPresent(String message) {
        super(message);
    }
}
