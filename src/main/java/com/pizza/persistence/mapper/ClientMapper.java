package com.pizza.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.pizza.domain.dtos.ClientDTO;
import com.pizza.persistence.model.Client;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    

    Client toEntity(ClientDTO clientDTO);
    ClientDTO toDto(Client client);

    List<ClientDTO>tDtos(List<Client> clients);

    void updateEntityFromDto(ClientDTO clientDTO, @MappingTarget Client client);

}
