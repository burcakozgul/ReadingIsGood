package com.example.readingisgood.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import com.example.readingisgood.types.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "order")
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";
    @Id
    private Long id;
    private Long customerId;
    private Map<Long, Integer> book = new HashMap<>();
    private OrderStatus orderStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalAmount;
}
