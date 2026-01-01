package com.pizza.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.domain.Exception.NotFoundException;
import com.pizza.domain.dtos.ClientDTO;
import com.pizza.domain.repository.ClientRepository;
import com.pizza.persistence.crud.ClientCRUD;
import com.pizza.persistence.mapper.ClientMapper;


@Repository
public class ClientEntityRepository implements ClientRepository {

    private final ClientCRUD clientCRUD;
    private final ClientMapper clientMapper;

    public ClientEntityRepository(ClientCRUD clientCRUD, ClientMapper clientMapper) {
        this.clientCRUD = clientCRUD;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> findAll() {
       return clientMapper.tDtos(clientCRUD.findAll());
    }

    @Override
    public ClientDTO getById(Long id) {
       return clientMapper.toDto(clientCRUD.findById(id).orElseThrow(
        () -> new NotFoundException("Cliente no encontrado")
       ));
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        var client = clientMapper.toEntity(clientDTO);
        var savedClient = clientCRUD.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        var existingClient = clientCRUD.findById(id).orElseThrow(
            () -> new NotFoundException("Cliente no encontrado")
        );
        clientMapper.updateEntityFromDto(clientDTO, existingClient);
        var updatedClient = clientCRUD.save(existingClient);
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void delete(Long id) {
        if (!clientCRUD.existsById(id)) {
            throw new NotFoundException("Cliente no encontrado");
        }
        clientCRUD.deleteById(id);
    }
    
}
