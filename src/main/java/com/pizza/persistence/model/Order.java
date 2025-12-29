package com.pizza.persistence.model;

import java.time.LocalDateTime;

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
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long clientId;
    private String method;
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2)")
    private Double total;
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;
}
