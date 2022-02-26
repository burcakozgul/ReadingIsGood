package com.example.readingisgood.types.requests;

import com.example.readingisgood.types.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {
    private Long orderId;
    private OrderStatus status;
}
