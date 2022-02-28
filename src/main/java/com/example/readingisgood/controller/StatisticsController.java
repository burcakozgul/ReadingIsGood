package com.example.readingisgood.controller;

import com.example.readingisgood.service.StatisticsService;
import com.example.readingisgood.types.responses.MonthlyOrderStatisticsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@Api(value = "Statistics Controller Class")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get monthly order statistics by userId method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public MonthlyOrderStatisticsResponse getMonthlyOrderStaticsByUserId(@PathVariable Long userId, @RequestParam int month,
                                                                         @RequestHeader("Authorization") String token) {
        return statisticsService.getMonthlyOrderStaticsByUserId(userId, month, token);
    }

}
