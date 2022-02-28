package com.example.readingisgood.repository;

import com.example.readingisgood.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    User findUserByMail(String mail);
}
