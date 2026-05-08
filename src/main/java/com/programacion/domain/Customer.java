package com.programacion.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends PanacheEntity {

    @Column(nullable = false, length = 120)
    public String fullName;

    @Column(nullable = false, unique = true, length = 150)
    public String email;
}
