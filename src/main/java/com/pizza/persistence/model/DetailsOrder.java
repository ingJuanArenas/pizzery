package com.pizza.persistence.model;

import java.util.List;

import org.hibernate.internal.build.AllowNonPortable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "details_order")
@Getter
@Setter
@NoArgsConstructor
public class DetailsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="order_id", nullable = false)
    private Long orderId;
    @Column(name= "pizza_id" , nullable = false)
    private Long pizzaId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(name="price_per_unit" ,nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double pricexunit;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pizza_id", insertable = false, updatable = false)
    private Pizza pizza;


}
