package com.pizza.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.domain.Exception.NotFoundException;
import com.pizza.domain.dtos.OrderDTO;
import com.pizza.domain.repository.OrderRepository;
import com.pizza.persistence.crud.DetailsOrderCRUD;
import com.pizza.persistence.crud.OrderCRUD;
import com.pizza.persistence.mapper.OrderMapper;
import com.pizza.persistence.model.DetailsOrder;
import com.pizza.persistence.model.Order;

import jakarta.transaction.Transactional;

@Repository
public class OrderEntityRepository implements OrderRepository {
    
    private final OrderCRUD orderCRUD;
    private final OrderMapper orderMapper;
    private final DetailsOrderCRUD detailsOrderCRUD;

    public OrderEntityRepository(OrderCRUD orderCRUD, OrderMapper orderMapper, DetailsOrderCRUD detailsOrderCRUD) {
        this.orderCRUD = orderCRUD;
        this.orderMapper = orderMapper;
        this.detailsOrderCRUD = detailsOrderCRUD;
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
@Transactional
public OrderDTO create(OrderDTO orderDTO) {

    Order orderEntity = orderMapper.toEntity(orderDTO);
    orderEntity.setOrderDate(LocalDate.now());

    // 🔥 1. GUARDAR Y RECIBIR EL OBJETO CON ID
    Order savedOrder = orderCRUD.save(orderEntity);

    // 🔥 2. VALIDAR QUE HAY DETAILS
    if (savedOrder.getDetailsOrders() != null) {

        System.out.println("DEBUG 1 :  ...." + savedOrder.getId());
System.out.println("DEBUG 2: ....." +savedOrder.getDetailsOrders());

        for (DetailsOrder d : savedOrder.getDetailsOrders()) {
            d.setOrderId(savedOrder.getId());   // 🔥 AHORA SÍ
        }

        detailsOrderCRUD.saveAll(savedOrder.getDetailsOrders());
    }

    // 🔥 3. DEVOLVER EL OBJETO GUARDADO
    return orderMapper.toDto(savedOrder);
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
