package com.example.readingisgood.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.example.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    Page<Order> findOrdersByCustomerId(Long customerId, Pageable pageable);

    @Query("{$and : [{'startDate': {$gte: ?0}}, {'endDate': {$lte: ?1}}] }")
    List<Order> findOrdersBetweenTwoDates(LocalDateTime startDate, LocalDateTime endDate);
}
