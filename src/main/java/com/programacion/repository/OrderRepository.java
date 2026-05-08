package com.programacion.repository;

import com.programacion.domain.CustomerOrder;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<CustomerOrder> {
}
