package com.pizza.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.domain.dtos.PizzaDTO;
import com.pizza.persistence.repository.PizzaEntityRepository;

@Service
public class PizzaService {
    private final PizzaEntityRepository pizzaRepository;

    public PizzaService(PizzaEntityRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaDTO> getAll() {
        return pizzaRepository.getAll();
    }
    public PizzaDTO getById(Long id) {
        return pizzaRepository.getById(id);
    }
    public PizzaDTO create(PizzaDTO pizzaDTO) {
        return pizzaRepository.create(pizzaDTO);
    }
    public PizzaDTO update(Long id, PizzaDTO pizzaDTO) {
        return pizzaRepository.update(id, pizzaDTO);
    }
    public void delete(Long id) {
        pizzaRepository.delete(id);
    }
}
