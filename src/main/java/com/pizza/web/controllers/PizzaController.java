package com.pizza.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.domain.dtos.PizzaDTO;
import com.pizza.domain.service.PizzaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        return ResponseEntity.ok(this.pizzaService.getAll());
    }


    @GetMapping("/{id}") 
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        return ResponseEntity.ok(this.pizzaService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<PizzaDTO> createPizza(@RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO createdPizza = this.pizzaService.create(pizzaDTO);
        return ResponseEntity.status(201).body(createdPizza);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PizzaDTO> updatePizza(@PathVariable Long id, @RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO updatedPizza = this.pizzaService.update(id, pizzaDTO);
        return ResponseEntity.ok(updatedPizza);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza( @PathVariable Long id) {
        this.pizzaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
