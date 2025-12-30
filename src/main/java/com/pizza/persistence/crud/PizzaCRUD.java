package com.pizza.persistence.crud;

import org.springframework.data.repository.ListCrudRepository;

import com.pizza.persistence.model.Pizza;

public interface PizzaCRUD extends ListCrudRepository<Pizza, Long> {
     
}
