package com.pizza.persistence.mapper;

import org.mapstruct.Mapper;

import com.pizza.domain.dtos.DetailsOrderDTO;
import com.pizza.persistence.model.DetailsOrder;

@Mapper(componentModel = "spring")
public interface DetailsMapper {
    DetailsOrder toEntity(DetailsOrderDTO dto);
    DetailsOrderDTO toDto(DetailsOrder entity);
}
