package com.example.readingisgood.types.responses;

import java.time.Month;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyOrderStatisticsResponse {
    private Month month;
    private int totalOrder;
    private int totalBook;
    private double totalAmount;
}
