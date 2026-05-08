package com.programacion.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem extends PanacheEntity {

    @ManyToOne(optional = false)
    public CustomerOrder order;

    @ManyToOne(optional = false)
    public Product product;

    @Column(nullable = false)
    public Integer quantity;

    @Column(nullable = false)
    public Double price;

    @Column(nullable = false)
    public Double subtotal;
}
