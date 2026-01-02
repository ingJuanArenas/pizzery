package com.pizza.persistence.crud;

import org.springframework.data.repository.ListCrudRepository;

import com.pizza.persistence.model.DetailsOrder;

public interface DetailsOrderCRUD  extends ListCrudRepository<DetailsOrder,Long>{

    
} 