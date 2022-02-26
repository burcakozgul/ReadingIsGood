package com.example.readingisgood.repository;

import com.example.readingisgood.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

    Customer findCustomerByMail(String mail);
}
