package com.pizza.persistence.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizza.domain.dtos.DetailsOrderDTO;

import jakarta.persistence.CascadeType;
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
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "client_id")
    private Long clientId;


    private String method;


    @Column(nullable = false, columnDefinition = "DECIMAL(8,2)")
    private Double total;


    @Column(name="order_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER )
    private List<DetailsOrder> detailsOrders = new ArrayList<>();
}
