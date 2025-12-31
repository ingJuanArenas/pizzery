package com.pizza.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.domain.Exception.NotFoundException;
import com.pizza.domain.dtos.OrderDTO;
import com.pizza.domain.repository.OrderRepository;
import com.pizza.persistence.crud.OrderCRUD;
import com.pizza.persistence.mapper.OrderMapper;
import com.pizza.persistence.model.Order;

@Repository
public class OrderEntityRepository implements OrderRepository {
    
    private final OrderCRUD orderCRUD;
    private final OrderMapper orderMapper;

    public OrderEntityRepository(OrderCRUD orderCRUD, OrderMapper orderMapper) {
        this.orderCRUD = orderCRUD;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAll() {
       return this.orderMapper.toDtos(orderCRUD.findAll());
    }

    @Override
    public List<OrderDTO> getByDate(LocalDate date) {
        return this.orderMapper.toDtos(this.orderCRUD.findByOrderDate(date).orElseThrow(
            ()-> new NotFoundException("No hay ordenes con esa fecha")
        ));
    }

    @Override
    public OrderDTO getById(Long id) {
        return this.orderMapper.toDto(this.orderCRUD.findById(id).orElseThrow(
            ()-> new NotFoundException("Orden no encontrada")
        ));
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order orderEntity = this.orderMapper.toEntity(orderDTO);
        orderEntity.setOrderDate(LocalDate.now());
        return this.orderMapper.toDto(this.orderCRUD.save(orderEntity));
    }

    @Override
    public OrderDTO update(Long id, OrderDTO orderDTO) {
        Order existingOrder = this.orderCRUD.findById(id).orElseThrow(
            ()-> new NotFoundException("Orden no encontrada")
        );
        this.orderMapper.updateEntityFromDto(orderDTO, existingOrder);
        return this.orderMapper.toDto(this.orderCRUD.save(existingOrder));
    }

    @Override
    public void delete(Long id) {
        //first verify if the id exists 
        Order existingOrder = this.orderCRUD.findById(id).orElseThrow(
            ()-> new NotFoundException("Orden no encontrada")
        );
        if (existingOrder != null) {
            this.orderCRUD.deleteById(id);
        }
    }


    
}
