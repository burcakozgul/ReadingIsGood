package com.example.readingisgood.service.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.types.OrderStatus;

public class StatisticsServiceTestData {

    public static List<Order> get_OrderList(int month) {
        Map<Long, Integer> book = new HashMap<>();
        book.put(1L, 2);
        Order order = Order.builder().id(1L)
            .userId(1L)
            .orderStatus(OrderStatus.DELIVERED)
            .startDate(LocalDateTime.of(2022, month, 1, 0, 0))
            .endDate(LocalDateTime.of(2022, month, 28, 0, 0))
            .book(book)
            .totalAmount(500).build();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        return orderList;
    }

}
