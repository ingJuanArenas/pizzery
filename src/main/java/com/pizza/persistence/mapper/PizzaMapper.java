package com.pizza.persistence.mapper;

import java.util.List;

import com.pizza.domain.dtos.PizzaDTO;
import com.pizza.persistence.model.Pizza;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PizzaMapper {

    @Mapping(target = "id", ignore = true)
    Pizza toEntity(PizzaDTO pizzaDTO);

    PizzaDTO toDto(Pizza pizza);
    List<PizzaDTO> toDtos(List<Pizza> pizzas);

    void updateEntityFromDto(PizzaDTO pizzaDTO, @MappingTarget Pizza pizza);


}