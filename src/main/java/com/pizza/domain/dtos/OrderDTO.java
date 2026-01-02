package com.pizza.domain.dtos;

import java.util.List;

public record OrderDTO(
    Long clientId,
    String method,
    Double total,
    List<DetailsOrderDTO> details
) {}

