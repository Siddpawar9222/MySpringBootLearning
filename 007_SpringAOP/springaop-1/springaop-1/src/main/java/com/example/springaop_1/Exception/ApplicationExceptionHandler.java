package com.example.springaop_1.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UniversalException.class)
    public Map<String, String> handleInvalidArgument(UniversalException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMg" ,exception.getMessage()) ;
        return errorMap;
    }
}