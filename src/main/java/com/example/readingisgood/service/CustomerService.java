package com.example.readingisgood.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.example.readingisgood.exception.CustomerException;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.types.CustomerStatus;
import com.example.readingisgood.types.requests.CreateCustomerRequest;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createCustomer(CreateCustomerRequest request) {
        validateParameters(request);
        Customer customer = customerRepository.findCustomerByMail(request.getMail());
        if (Objects.nonNull(customer)) {
            throw new CustomerException("Customer exist", "ERR_C1");
        }
        Customer newCustomer = Customer.builder().id(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME))
            .name(request.getName())
            .surname(request.getSurname())
            .mail(request.getMail())
            .phone(request.getPhone())
            .password(passwordEncoder.encode(request.getPassword()))
            .address(request.getAddress())
            .creditCard(request.getCreditCard())
            .createdDate(LocalDateTime.now())
            .customerStatus(CustomerStatus.ACTIVE).build();
        customerRepository.save(newCustomer);
    }

    public List<Order> getAllOrdersByCustomerId(Long customerId, int page, int size) {
        customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer does not exist", "ERR_C2"));
        Pageable paging = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findOrdersByCustomerId(customerId, paging);
        if (orderPage.isEmpty()) {
            throw new CustomerException("Customer does not have order", "ERR_C3");
        }
        return orderPage.getContent();
    }

    private void validateParameters(CreateCustomerRequest request) {
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
            throw new CustomerException("Missing parameters: " + Arrays.toString(fieldName.toArray()), "ERR_C4");
        }
    }
}
