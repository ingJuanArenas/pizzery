package com.pizza.domain.Exception;

public class AlreadyExistsException  extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
    
}
