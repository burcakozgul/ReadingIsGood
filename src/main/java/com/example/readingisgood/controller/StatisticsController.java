package com.example.readingisgood.controller;

import com.example.readingisgood.service.StatisticsService;
import com.example.readingisgood.types.responses.MonthlyOrderStatisticsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@Api(value = "Statistics Controller Class")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/{customerId}")
    @ApiOperation(value = "Get monthly order statistics by customerId method")
    public MonthlyOrderStatisticsResponse getMonthlyOrderStaticsByCustomerId(@PathVariable Long customerId, @RequestParam int month) {
        return statisticsService.getMonthlyOrderStaticsByCustomerId(customerId, month);
    }

}
