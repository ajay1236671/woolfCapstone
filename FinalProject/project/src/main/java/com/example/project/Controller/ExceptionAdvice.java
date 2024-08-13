package com.example.project.Controller;

import com.example.project.Exceptions.NotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> someException(Exception e){

        return new ResponseEntity<>("RESOURCE NOT FOUND",HttpStatus.NOT_FOUND);
    }
}
