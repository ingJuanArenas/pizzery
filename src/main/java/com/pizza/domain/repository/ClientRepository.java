package com.pizza.domain.repository;

import java.util.List;

import com.pizza.domain.dtos.ClientDTO;

public interface ClientRepository {
    
    // Define repository methods here
     List<ClientDTO> findAll();
     ClientDTO getById(Long id);
    ClientDTO save(ClientDTO clientDTO);
    ClientDTO update(Long id, ClientDTO clientDTO);
    void delete(Long id);
}
