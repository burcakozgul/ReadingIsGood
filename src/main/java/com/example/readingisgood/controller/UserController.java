package com.example.readingisgood.controller;

import java.util.List;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.service.UserService;
import com.example.readingisgood.types.requests.CreateUserRequest;
import com.example.readingisgood.types.responses.DefaultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(value = "User Controller Class")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    @ApiOperation(value = "New User creating method")
    public DefaultResponse createUser(@RequestBody CreateUserRequest request) {
        DefaultResponse defaultResponse = new DefaultResponse();
        userService.createUser(request);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("User successfully created");
        return defaultResponse;
    }


    @GetMapping("/{userId}")
    @ApiOperation(value = "Get all orders by userId method")
    public List<Order> getAllOrdersByUserId(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "3") int size) {
        return userService.getAllOrdersByUserId(userId, page, size);
    }
}
