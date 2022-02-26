package com.example.readingisgood.types.requests;

import com.example.readingisgood.types.Address;
import com.example.readingisgood.types.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String password;
    private Address address;
    private CreditCard creditCard;
}
