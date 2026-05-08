package com.programacion.service;

import com.programacion.domain.*;
import com.programacion.dto.CreateOrderRequest;
import com.programacion.repository.CustomerRepository;
import com.programacion.repository.OrderRepository;
import com.programacion.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;

@ApplicationScoped
public class OrderService {

    @Inject
    ProductRepository productRepository;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    OrderRepository orderRepository;

    @Transactional
    public CustomerOrder createOrder(CreateOrderRequest request) {
        if (request == null || request.items == null || request.items.isEmpty()) {
            throw new BadRequestException("El pedido debe tener al menos un item");
        }

        Customer customer = customerRepository.findByIdOptional(request.customerId)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        CustomerOrder order = new CustomerOrder();
        order.customer = customer;
        order.createdAt = LocalDateTime.now();
        order.total = 0d;

        for (CreateOrderRequest.OrderItemRequest reqItem : request.items) {
            Product product = productRepository.findByIdOptional(reqItem.productId)
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + reqItem.productId));

            if (reqItem.quantity == null || reqItem.quantity <= 0) {
                throw new BadRequestException("Cantidad inválida para producto " + reqItem.productId);
            }
            if (product.stock < reqItem.quantity) {
                throw new BadRequestException("Stock insuficiente para producto " + product.id);
            }

            product.stock -= reqItem.quantity;

            OrderItem item = new OrderItem();
            item.order = order;
            item.product = product;
            item.quantity = reqItem.quantity;
            item.price = product.price;
            item.subtotal = product.price * reqItem.quantity;

            order.items.add(item);
            order.total += item.subtotal;
        }

        orderRepository.persist(order);
        return order;
    }
}
