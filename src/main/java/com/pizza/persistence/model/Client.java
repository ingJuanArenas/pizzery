package com.pizza.persistence.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @Column(nullable = false, length = 15)
    private Long id;
    @Column(nullable = false, length = 70)
    private String name;
    @Column(nullable = false, length = 60)
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
}
