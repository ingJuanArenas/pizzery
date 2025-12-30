package com.pizza.domain.Exception;

public class NotFoundException  extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    
}
