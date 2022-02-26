package com.example.readingisgood.controller;

import java.util.List;
import com.example.readingisgood.exception.OrderException;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.service.OrderService;
import com.example.readingisgood.types.requests.CreateOrderRequest;
import com.example.readingisgood.types.requests.GetOrdersBetweenDatesRequest;
import com.example.readingisgood.types.requests.UpdateOrderStatusRequest;
import com.example.readingisgood.types.responses.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public DefaultResponse createOrder(@RequestBody CreateOrderRequest request) throws OrderException {
        DefaultResponse defaultResponse = new DefaultResponse();
        orderService.createOrder(request);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Order successfully created");
        return defaultResponse;
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/orderStatus")
    public DefaultResponse updateOrderStatus(@RequestBody UpdateOrderStatusRequest request) {
        DefaultResponse defaultResponse = new DefaultResponse();
        orderService.updateOrderStatus(request);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Order status successfully updated");
        return defaultResponse;
    }

    @GetMapping("/ordersBetweenDates")
    public List<Order> getOrdersBetweenDates(@RequestBody GetOrdersBetweenDatesRequest request) {
        return orderService.getOrdersBetweenDates(request);
    }

}
