package com.example.readingisgood.controller;

import com.example.readingisgood.service.StatisticsService;
import com.example.readingisgood.types.responses.MonthlyOrderStatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/{customerId}")
    public MonthlyOrderStatisticsResponse getMonthlyOrderStaticsByCustomerId(@PathVariable Long customerId, @RequestParam int month) {
        return statisticsService.getMonthlyOrderStaticsByCustomerId(customerId, month);
    }

}
