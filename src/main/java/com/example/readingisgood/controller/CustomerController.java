package com.example.readingisgood.controller;

import java.util.List;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.service.CustomerService;
import com.example.readingisgood.types.requests.CreateCustomerRequest;
import com.example.readingisgood.types.responses.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/signUp")
    public DefaultResponse createCustomer(@RequestBody CreateCustomerRequest request) {
        DefaultResponse defaultResponse = new DefaultResponse();
        customerService.createCustomer(request);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Customer successfully created");
        return defaultResponse;
    }


    @GetMapping("/{customerId}")
    public List<Order> getAllOrdersByCustomerId(@PathVariable Long customerId, @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size) {
        return customerService.getAllOrdersByCustomerId(customerId, page, size);
    }
}
