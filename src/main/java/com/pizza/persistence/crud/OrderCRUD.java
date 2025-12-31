package com.pizza.persistence.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.pizza.persistence.model.Order;

public interface OrderCRUD extends ListCrudRepository<Order,Long> {
   Optional<List<Order>>findByOrderDate(LocalDate orderDate);
}
