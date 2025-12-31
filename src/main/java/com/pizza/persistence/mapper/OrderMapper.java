package com.pizza.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.pizza.domain.dtos.OrderDTO;
import com.pizza.persistence.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDto(Order order);

    List<OrderDTO> toDtos(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    void updateEntityFromDto(OrderDTO orderDTO, @MappingTarget Order order);

}
