package com.pizza.web.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.pizza.domain.Exception.AlreadyExistsException;
import com.pizza.domain.Exception.NotFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException ex) {
        Error error = new Error("Not Found", ex.toString());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Error> handleAlreadyExistsException(AlreadyExistsException ex) {
        Error error = new Error("Already Exists", ex.toString());
        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Error> handleNoResourceFoundException(NoResourceFoundException ex) {
        Error error = new Error("Not Found", ex.toString());
        return ResponseEntity.status(404).body(error);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Error error = new Error("Data Integrity Violation", ex.toString());
        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(Exception ex) {
        Error error = new Error("Internal Server Error", ex.toString());
        return ResponseEntity.status(500).body(error);
    }
}
