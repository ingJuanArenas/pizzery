package com.pizza.persistence.crud;

import org.springframework.data.repository.ListCrudRepository;

import com.pizza.persistence.model.Client;

public interface ClientCRUD extends ListCrudRepository<Client, Long>{
    
}
