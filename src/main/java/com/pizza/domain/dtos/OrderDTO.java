package com.pizza.domain.dtos;

import java.util.List;

public record OrderDTO(
    Long clientId,
    String method,
    List<DetailsOrderDTO> details,
    Double total
) {}

