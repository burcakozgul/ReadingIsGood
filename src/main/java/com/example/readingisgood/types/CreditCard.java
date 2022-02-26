package com.example.readingisgood.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {
    private String cardNumber;
    private int cvvNumber;
    private String cardHolder;
    private int expiredMonth;
    private int expiredYear;
}
