package com.pizza.web.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.domain.dtos.OrderDTO;
import com.pizza.domain.service.OrderService;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<OrderDTO>> getOrderByDate(@PathVariable String date) {
        LocalDate dateTime= LocalDate.parse(date);
        return ResponseEntity.ok(this.orderService.getByDate(dateTime));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.getById(id));
    }

    @PostMapping //TODO: verificar que el cliente exista
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = this.orderService.create(orderDTO);
        return ResponseEntity.status(201).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id,@RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = this.orderService.update(id, orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }   


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        this.orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
