package com.example.readingisgood.service.data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.types.OrderStatus;
import com.example.readingisgood.types.requests.CreateOrderRequest;
import com.example.readingisgood.types.requests.UpdateOrderStatusRequest;

public class OrderServiceTestData {

    public static CreateOrderRequest get_CreateOrderRequest() {
        Map<Long, Integer> book = new HashMap<>();
        book.put(1L, 2);
        CreateOrderRequest request = CreateOrderRequest.builder()
            .customerId(1L)
            .book(book)
            .build();
        return request;
    }

    public static Optional<Order> get_DeliveredOrder() {
        Map<Long, Integer> book = new HashMap<>();
        book.put(1L, 2);
        Optional<Order> order = Optional.ofNullable(Order.builder()
            .id(1L)
            .orderStatus(OrderStatus.DELIVERED)
            .customerId(1L)
            .totalAmount(345.6)
            .startDate(LocalDateTime.now().minusHours(1))
            .endDate(LocalDateTime.now())
            .book(book)
            .build());
        return order;
    }

    public static UpdateOrderStatusRequest get_UpdateOrderStatusRequest() {
        UpdateOrderStatusRequest request = UpdateOrderStatusRequest.builder()
            .orderId(1L)
            .status(OrderStatus.DELIVERED)
            .build();
        return request;
    }

    public static Optional<Order> get_ShippedOrder() {
        Map<Long, Integer> book = new HashMap<>();
        book.put(1L, 2);
        Optional<Order> order = Optional.ofNullable(Order.builder()
            .id(1L)
            .orderStatus(OrderStatus.SHIPPED)
            .customerId(1L)
            .totalAmount(345.6)
            .startDate(LocalDateTime.now().minusHours(1))
            .endDate(LocalDateTime.now())
            .book(book)
            .build());
        return order;
    }
}
