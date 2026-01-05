package com.pizza.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.domain.Exception.NotFoundException;
import com.pizza.domain.dtos.OrderDTO;
import com.pizza.domain.repository.OrderRepository;
import com.pizza.persistence.crud.DetailsOrderCRUD;
import com.pizza.persistence.crud.OrderCRUD;
import com.pizza.persistence.crud.PizzaCRUD;
import com.pizza.persistence.mapper.OrderMapper;
import com.pizza.persistence.model.DetailsOrder;
import com.pizza.persistence.model.Order;
import com.pizza.persistence.model.Pizza;

import jakarta.transaction.Transactional;

@Repository
public class OrderEntityRepository implements OrderRepository {
    
    private final OrderCRUD orderCRUD;
    private final OrderMapper orderMapper;
    private final DetailsOrderCRUD detailsOrderCRUD;
    private final PizzaCRUD pizzaCRUD;

    public OrderEntityRepository(OrderCRUD orderCRUD, OrderMapper orderMapper, DetailsOrderCRUD detailsOrderCRUD, PizzaCRUD pizzaCRUD) {
        this.orderCRUD = orderCRUD;
        this.orderMapper = orderMapper;
        this.detailsOrderCRUD = detailsOrderCRUD;
        this.pizzaCRUD = pizzaCRUD;
    }
    @Override
    public List<OrderDTO> getAll() {
       return this.orderMapper.toDtos(orderCRUD.findAll());
    }

    @Override
    public List<OrderDTO> getByDate(LocalDate date) {
        return this.orderMapper.toDtos(this.orderCRUD.findByOrderDate(date).get());
    }

    @Override
    public OrderDTO getById(Long id) {
        return this.orderMapper.toDto(this.orderCRUD.findById(id).orElseThrow(
            ()-> new NotFoundException("Orden no encontrada")
        ));
    }

@Override
@Transactional
public OrderDTO create(OrderDTO orderDTO) {

    Order orderEntity = orderMapper.toEntity(orderDTO);
    orderEntity.setOrderDate(LocalDate.now());
    orderEntity.setTotal(calculateTotalPrice(orderEntity.getDetailsOrders()));
    System.out.println("Total price calculated: " + orderEntity.getTotal());
    Order savedOrder = orderCRUD.save(orderEntity);
   //  Double total = 0.0; 

    if (savedOrder.getDetailsOrders() != null) {

        for (DetailsOrder d : savedOrder.getDetailsOrders()) {
            d.setOrderId(savedOrder.getId());  

            var pizza = getPizzaById(d.getPizzaId());
            d.setPricexunit(pizza.getPrice());
        }

        detailsOrderCRUD.saveAll(savedOrder.getDetailsOrders());
    }

    
    return orderMapper.toDto(savedOrder);
}



    @Override
    @Transactional
    public void delete(Long id) {
        //first verify if the id exists 
        Order existingOrder = this.orderCRUD.findById(id).orElseThrow(
            ()-> new NotFoundException("Orden no encontrada")
        );
            this.orderCRUD.delete(existingOrder);

    }


    private Pizza getPizzaById(Long pizzaId) {
        return pizzaCRUD.findById(pizzaId).orElseThrow(
            () -> new NotFoundException("Pizza no encontrada")
        );
    }
    private double calculateTotalPrice(List<DetailsOrder> detailsOrders) {
        return detailsOrders.stream().mapToDouble(detail -> {

                    Pizza pizza = getPizzaById(detail.getPizzaId());
                    return pizza.getPrice() * detail.getQuantity();
                })
                .sum();
    }


    
}
