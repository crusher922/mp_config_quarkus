package com.programacion.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class CustomerOrder extends PanacheEntity {

    @ManyToOne(optional = false)
    public Customer customer;

    @Column(nullable = false)
    public LocalDateTime createdAt;

    @Column(nullable = false)
    public Double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrderItem> items = new ArrayList<>();
}
