package com.example.readingisgood.model;

import java.time.LocalDateTime;
import com.example.readingisgood.types.Address;
import com.example.readingisgood.types.CreditCard;
import com.example.readingisgood.types.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "customer")
public class Customer {

    @Transient
    public static final String SEQUENCE_NAME = "customers_sequence";
    @Id
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String password;
    private LocalDateTime createdDate;
    private CustomerStatus customerStatus;
    private Address address;
    private CreditCard creditCard;
}
