package com.example.readingisgood.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.example.readingisgood.exception.UserException;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.model.User;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.repository.UserRepository;
import com.example.readingisgood.types.UserStatus;
import com.example.readingisgood.types.requests.CreateUserRequest;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(CreateUserRequest request) {
        validateParameters(request);
        User user = userRepository.findUserByMail(request.getMail());
        if (Objects.nonNull(user)) {
            throw new UserException("User exist", "ERR_C1");
        }
        User newUser = User.builder().id(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME))
            .name(request.getName())
            .surname(request.getSurname())
            .mail(request.getMail())
            .phone(request.getPhone())
            .password(passwordEncoder.encode(request.getPassword()))
            .address(request.getAddress())
            .creditCard(request.getCreditCard())
            .createdDate(LocalDateTime.now())
            .userStatus(UserStatus.ACTIVE)
            .roles(request.getMail().equals("burcakozgul@gmail.com") ? List.of("1", "2") : List.of("1")).build();
        userRepository.save(newUser);
    }

    public List<Order> getAllOrdersByUserId(Long userId, int page, int size) {
        userRepository.findById(userId).orElseThrow(() -> new UserException("User does not exist", "ERR_C2"));
        Pageable paging = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findOrdersByUserId(userId, paging);
        if (orderPage.isEmpty()) {
            throw new UserException("User does not have order", "ERR_C3");
        }
        return orderPage.getContent();
    }

    private void validateParameters(CreateUserRequest request) {
        List<String> fieldName = new ArrayList<>();
        if (Strings.isNullOrEmpty(request.getName())) {
            fieldName.add("name");
        }
        if (Strings.isNullOrEmpty(request.getPassword())) {
            fieldName.add("password");
        }
        if (Strings.isNullOrEmpty(request.getMail())) {
            fieldName.add("mail");
        }
        if (request.getCreditCard() == null) {
            fieldName.add("creditCard");
        }
        if (request.getAddress() == null) {
            fieldName.add("address");
        }
        if (!fieldName.isEmpty()) {
            throw new UserException("Missing parameters: " + Arrays.toString(fieldName.toArray()), "ERR_C4");
        }
    }
}
