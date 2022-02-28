package com.example.readingisgood.service.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.readingisgood.model.User;
import com.example.readingisgood.types.Address;
import com.example.readingisgood.types.CreditCard;
import com.example.readingisgood.types.UserStatus;
import com.example.readingisgood.types.requests.CreateUserRequest;

public class UserServiceTestData {

    public static CreateUserRequest get_CreateUserRequest() {
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
        CreateUserRequest request = CreateUserRequest.builder()
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

    public static Optional<User> get_User() {
        Optional<User> user = Optional.ofNullable(User.builder()
            .id(1L)
            .name("elif")
            .surname("ozgul")
            .userStatus(UserStatus.ACTIVE)
            .createdDate(LocalDateTime.of(2022, 2, 2, 0, 0))
            .mail("burcakozgul@gmail.com")
            .roles(List.of("1","2")).build());
        return user;
    }

    public static User get_User2(){
        User user = User.builder()
            .id(1L)
            .name("elif")
            .surname("ozgul")
            .userStatus(UserStatus.ACTIVE)
            .createdDate(LocalDateTime.of(2022, 2, 2, 0, 0))
            .mail("burcakozgul@gmail.com")
            .roles(List.of("1","2")).build();
        return user;
    }

}
