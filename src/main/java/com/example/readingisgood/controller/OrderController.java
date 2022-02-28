package com.example.readingisgood.controller;

import java.util.List;
import com.example.readingisgood.exception.OrderException;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.service.OrderService;
import com.example.readingisgood.types.requests.CreateOrderRequest;
import com.example.readingisgood.types.requests.GetOrdersBetweenDatesRequest;
import com.example.readingisgood.types.requests.UpdateOrderStatusRequest;
import com.example.readingisgood.types.responses.DefaultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api("Order Controller Class")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @ApiOperation(value = "New Order creating method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public DefaultResponse createOrder(@RequestBody CreateOrderRequest request) throws OrderException {
        DefaultResponse defaultResponse = new DefaultResponse();
        orderService.createOrder(request);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Order successfully created");
        return defaultResponse;
    }

    @GetMapping("/{orderId}")
    @ApiOperation(value = "Get order by orderId method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/orderStatus")
    @ApiOperation(value = "Update order by orderId and status method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public DefaultResponse updateOrderStatus(@RequestBody UpdateOrderStatusRequest request, @RequestHeader("Authorization") String token) {
        DefaultResponse defaultResponse = new DefaultResponse();
        orderService.updateOrderStatus(request, token);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Order status successfully updated");
        return defaultResponse;
    }

    @GetMapping("/ordersBetweenDates")
    @ApiOperation(value = "Get orders between two dates method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public List<Order> getOrdersBetweenDates(@RequestBody GetOrdersBetweenDatesRequest request) {
        return orderService.getOrdersBetweenDates(request);
    }

}
