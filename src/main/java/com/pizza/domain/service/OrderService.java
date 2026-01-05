package com.pizza.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.domain.Exception.NotFoundException;
import com.pizza.domain.dtos.OrderDTO;
import com.pizza.persistence.repository.OrderEntityRepository;


@Service
public class OrderService {
    private final OrderEntityRepository orderEntityRepository;

    public OrderService(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    public List<OrderDTO> getAll() {
       List<OrderDTO> orders =  this.orderEntityRepository.getAll();
       if (orders.isEmpty()) {
         throw new NotFoundException("No hay ordenes");
       }
       return orders;
    }

    public List<OrderDTO> getByDate(LocalDate date) {
        List<OrderDTO> orders =  this.orderEntityRepository.getByDate(date);

        if (orders.isEmpty()) {
         throw new NotFoundException("No hay ordenes con esa fecha");
        }
        return orders ;
    }

    public OrderDTO getById(Long id) {
        return this.orderEntityRepository.getById(id);
    }

    public OrderDTO create(OrderDTO orderDTO) {
        return this.orderEntityRepository.create(orderDTO);
    }


    public void delete(Long id) {
        this.orderEntityRepository.delete(id);
    }
}
