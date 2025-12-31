package com.pizza.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.pizza.domain.dtos.OrderDTO;

public interface OrderRepository {

    List<OrderDTO> getAll();
    List<OrderDTO> getByDate(LocalDate date);
    OrderDTO getById(Long id);
    OrderDTO create(OrderDTO orderDTO);
    OrderDTO update(Long id, OrderDTO orderDTO);
    void delete(Long id);
    
} 
