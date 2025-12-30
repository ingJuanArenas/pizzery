package com.pizza.web.exceptions;

public record Error(
    String type,
    String message
) {
    
}
