package com.pizza.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pizza.domain.dtos.OrderDTO;
import com.pizza.persistence.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(source = "details", target = "detailsOrders")
    Order toEntity(OrderDTO orderDTO);

     @Mapping(source = "detailsOrders", target = "details")
    OrderDTO toDto(Order order);

    List<OrderDTO> toDtos(List<Order> orders);



}
