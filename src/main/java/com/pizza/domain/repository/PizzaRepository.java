package com.pizza.domain.repository;

import java.util.List;

import com.pizza.domain.dtos.PizzaDTO;

public interface PizzaRepository {
    List<PizzaDTO> getAll();
    PizzaDTO getById(Long id);
    PizzaDTO create(PizzaDTO pizzaDTO);
    PizzaDTO update(Long id, PizzaDTO pizzaDTO);
    void delete(Long id);
}
