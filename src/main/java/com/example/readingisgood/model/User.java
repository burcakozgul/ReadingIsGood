package com.example.readingisgood.model;

import java.time.LocalDateTime;
import java.util.List;
import com.example.readingisgood.types.Address;
import com.example.readingisgood.types.CreditCard;
import com.example.readingisgood.types.UserStatus;
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
@Document(collection = "user")
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String password;
    private LocalDateTime createdDate;
    private UserStatus userStatus;
    private Address address;
    private CreditCard creditCard;
    private List<String> roles;
}
