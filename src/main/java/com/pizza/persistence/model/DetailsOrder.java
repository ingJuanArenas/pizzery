package com.pizza.persistence.model;

import org.hibernate.internal.build.AllowNonPortable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(nullable = false)
    private Long orderId;
    @Column(nullable = false)
    private Long pizzaId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double pricexunit;

}
