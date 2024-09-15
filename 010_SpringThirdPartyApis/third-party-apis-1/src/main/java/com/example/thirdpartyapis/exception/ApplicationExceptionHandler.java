package com.example.thirdpartyapis.exception;


import com.example.thirdpartyapis.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Response> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        Response response = new Response();
        response.setMessage("Resource not found : " + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
//        Map<String, String> errorMap = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//        });
//        return errorMap;
//    }


    //For validation-api
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Response> handleInvalidArgument(MethodArgumentNotValidException exception) {
//        Response response = new Response();
//        Map<String, String> errorMap = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//        });
//
//        response.setMessage("Invalid body : " + errorMap.values());
//        response.setResultCode(HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response> handleInvalidArgument(MissingServletRequestParameterException exception) {
        Response response = new Response();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleBadRequestException(ResourceNotFoundException exception) {
        Response response = new Response();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Response> handleGlobalException(GlobalException exception) {
        Response response = new Response();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
