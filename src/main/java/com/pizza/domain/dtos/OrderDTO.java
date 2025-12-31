package com.pizza.domain.dtos;


public record OrderDTO(
     Long clientId,
     String method,
     Double total
) {
    
}
