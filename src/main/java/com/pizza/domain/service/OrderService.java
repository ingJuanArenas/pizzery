package com.pizza.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.domain.dtos.OrderDTO;
import com.pizza.persistence.repository.OrderEntityRepository;

@Service
public class OrderService {
    private final OrderEntityRepository orderEntityRepository;

    public OrderService(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    public List<OrderDTO> getAll() {
       return this.orderEntityRepository.getAll();
    }

    public List<OrderDTO> getByDate(LocalDate date) {
        return this.orderEntityRepository.getByDate(date);
    }

    public OrderDTO getById(Long id) {
        return this.orderEntityRepository.getById(id);
    }

    public OrderDTO create(OrderDTO orderDTO) {
        return this.orderEntityRepository.create(orderDTO);
    }

    public OrderDTO update(Long id, OrderDTO orderDTO) {
        return this.orderEntityRepository.update(id, orderDTO);
    }
    public void delete(Long id) {
        this.orderEntityRepository.delete(id);
    }
}
