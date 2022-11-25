package com.example.springbootredis.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {RuntimeException.class})
    public String handler(Exception exception) {
        return exception.getMessage();
    }
}
