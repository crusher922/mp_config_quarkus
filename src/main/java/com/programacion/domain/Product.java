package com.programacion.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Product extends PanacheEntity {

    @Column(nullable = false, length = 120)
    public String name;

    @Column(length = 500)
    public String description;

    @Column(nullable = false)
    public Double price;

    @Column(nullable = false)
    public Integer stock;
}
