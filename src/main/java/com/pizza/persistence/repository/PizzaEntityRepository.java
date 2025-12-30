package com.pizza.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.domain.Exception.NotFoundException;
import com.pizza.domain.dtos.PizzaDTO;
import com.pizza.domain.repository.PizzaRepository;
import com.pizza.persistence.crud.PizzaCRUD;
import com.pizza.persistence.mapper.PizzaMapper;

@Repository
public class PizzaEntityRepository implements PizzaRepository {

    private final PizzaCRUD pizzaCRUD;
    private final PizzaMapper pizzaMapper;

    public PizzaEntityRepository(PizzaCRUD pizzaCRUD, PizzaMapper pizzaMapper) {
        this.pizzaCRUD = pizzaCRUD;
        this.pizzaMapper = pizzaMapper;
    }


    @Override
    public List<PizzaDTO> getAll() {
        return this.pizzaMapper.toDtos(this.pizzaCRUD.findAll());
    }

    @Override
    public PizzaDTO getById(Long id) {
        return this.pizzaMapper.toDto(this.pizzaCRUD.findById(id).orElseThrow( 
            () -> new NotFoundException("Pizza no encontrada")));
    }

    @Override
    public PizzaDTO create(PizzaDTO pizzaDTO) {
        var newPizza= this.pizzaMapper.toEntity(pizzaDTO);
        return this.pizzaMapper.toDto(this.pizzaCRUD.save(newPizza));
    }

    @Override
    public PizzaDTO update(Long id, PizzaDTO pizzaDTO) {
        var existingPizza = this.pizzaCRUD.findById(id).orElseThrow(
            () -> new NotFoundException("Pizza no encontrada"));
        this.pizzaMapper.updateEntityFromDto(pizzaDTO, existingPizza);
        return this.pizzaMapper.toDto(this.pizzaCRUD.save(existingPizza));
    }

    @Override
    public void delete(Long id) {
        if (!this.pizzaCRUD.existsById(id)) {
            throw new NotFoundException("Pizza no encontrada");
        }
        this.pizzaCRUD.deleteById(id);
    }
    
}
