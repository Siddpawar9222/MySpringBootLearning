package com.example.spring_caching_1.advice;

import com.example.spring_caching_1.exception.EmployeeNotFoundException;
import com.example.spring_caching_1.exception.EmployeesNotPresent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String, String> errorMap = new HashMap<>() ;
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
      return  errorMap ;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public Map<String, String> handleBusinessError(EmployeeNotFoundException exception){
        Map<String, String> errorMap = new HashMap<>() ;
        errorMap.put("errorMg" ,exception.getMessage()) ;
      return  errorMap ;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmployeesNotPresent.class)
    public Map<String, String> handleBusinessError(EmployeesNotPresent exception){
        Map<String, String> errorMap = new HashMap<>() ;
        errorMap.put("errorMg" ,exception.getMessage()) ;
      return  errorMap ;
    }
}
