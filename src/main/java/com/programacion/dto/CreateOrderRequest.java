package com.programacion.dto;

import java.util.List;

public class CreateOrderRequest {
    public Long customerId;
    public List<OrderItemRequest> items;

    public static class OrderItemRequest {
        public Long productId;
        public Integer quantity;
    }
}
