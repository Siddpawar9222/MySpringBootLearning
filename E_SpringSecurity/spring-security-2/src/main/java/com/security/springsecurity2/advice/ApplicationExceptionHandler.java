package com.security.springsecurity2.advice;

import com.security.springsecurity2.exception.BadRequestException;
import com.security.springsecurity2.exception.ResourceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Map<String, String> handleBadRequestException(BadRequestException exception){
        Map<String, String> map = new HashMap<>();
        map.put("Response" , exception.getMessage()) ;
        return  map ;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResourceUnavailableException.class)
    public Map<String, String> handleResourceUnavailableException(ResourceUnavailableException exception){
        Map<String, String> map = new HashMap<>();
        map.put("Response" , exception.getMessage()) ;
        return  map ;
    }
}
