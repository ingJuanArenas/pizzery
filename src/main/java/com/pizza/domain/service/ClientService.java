package com.pizza.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.domain.dtos.ClientDTO;
import com.pizza.persistence.repository.ClientEntityRepository;

@Service
public class ClientService {

    private final ClientEntityRepository clientRepository;

    public ClientService(ClientEntityRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAll() {
            return this.clientRepository.findAll();
    }

    public ClientDTO getById(Long id) {
        return this.clientRepository.getById(id);
    }

    public ClientDTO create(ClientDTO clientDTO) {
        return this.clientRepository.save(clientDTO);
    }

    public ClientDTO update(Long id, ClientDTO clientDTO) {
        return this.clientRepository.update(id, clientDTO);
    }

    public void delete(Long id) {
        this.clientRepository.delete(id);
    }
    
}