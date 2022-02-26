package com.example.readingisgood.repository;

import com.example.readingisgood.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    Book findByIsbnNumber(String isbnNumber);
}
