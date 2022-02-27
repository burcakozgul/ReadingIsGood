package com.example.readingisgood.service.data;

import java.time.LocalDateTime;
import java.util.Optional;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.types.Address;
import com.example.readingisgood.types.CreditCard;
import com.example.readingisgood.types.CustomerStatus;
import com.example.readingisgood.types.requests.CreateCustomerRequest;

public class CustomerServiceTestData {

    public static CreateCustomerRequest get_CreateCustomerRequest() {
        Address address = Address.builder().city("Istanbul")
            .apartment("Menekşe")
            .street("Karanfil")
            .postalCode("34667")
            .build();
        CreditCard creditCard = CreditCard.builder().cardHolder("burcak namver")
            .cardNumber("1234567865324444")
            .cvvNumber(123)
            .expiredMonth(5)
            .expiredYear(2024)
            .build();
        CreateCustomerRequest request = CreateCustomerRequest.builder()
            .name("Burcak")
            .surname("Namver")
            .mail("burcak@gmail.com")
            .password("test1234")
            .phone("5559997788")
            .address(address)
            .creditCard(creditCard)
            .build();
        return request;
    }

    public static Optional<Customer> get_Customer() {
        Optional<Customer> customer = Optional.ofNullable(Customer.builder()
            .id(1L)
            .name("elif")
            .surname("ozgul")
            .customerStatus(CustomerStatus.ACTIVE)
            .createdDate(LocalDateTime.of(2022, 2, 2, 0, 0))
            .mail("test@gmail.com").build());
        return customer;
    }

}
