package com.pizza.domain.dtos;

public record DetailsOrderDTO(
    Long pizzaId,
    Integer quantity,
    Double pricexunit
) {}
