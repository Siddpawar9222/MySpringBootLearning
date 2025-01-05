package com.test.springtest.advice;

import com.test.springtest.exception.EmployeeNotFoundException;
import com.test.springtest.exception.EmployeesNotPresent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>() ;
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
      return  new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST) ;
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBusinessError(EmployeeNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>() ;
        errorMap.put("errorMg" ,exception.getMessage()) ;
      return  new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND) ;
    }
    @ExceptionHandler(EmployeesNotPresent.class)
    public ResponseEntity<Map<String, String>> handleBusinessError(EmployeesNotPresent exception){
        Map<String, String> errorMap = new HashMap<>() ;
        errorMap.put("errorMg" ,exception.getMessage()) ;
      return  new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND) ;
    }
}
