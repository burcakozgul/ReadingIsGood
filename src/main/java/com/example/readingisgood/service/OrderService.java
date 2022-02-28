package com.example.readingisgood.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.example.readingisgood.exception.OrderException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.types.OrderStatus;
import com.example.readingisgood.types.requests.CreateOrderRequest;
import com.example.readingisgood.types.requests.GetOrdersBetweenDatesRequest;
import com.example.readingisgood.types.requests.UpdateOrderStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public void createOrder(CreateOrderRequest request) throws OrderException {
        validateParameters(request);
        customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new OrderException("Customer does not exist", "ERR_O1"));
        for (Long book : request.getBook().keySet()) {
            bookRepository.findById(book).orElseThrow(() -> new OrderException("Book does not exist", "ERR_O2"));
        }
        checkStock(request.getBook());
        double totalAmount = sumPrices(request.getBook());
        Order order = Order.builder().id(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME))
            .customerId(request.getCustomerId())
            .book(request.getBook())
            .orderStatus(OrderStatus.APPROVED)
            .startDate(LocalDateTime.now())
            .totalAmount(totalAmount).build();
        orderRepository.save(order);
        updateStock(request.getBook());
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderException("Order does not exist", "ERR_O3"));
    }

    private double sumPrices(Map<Long, Integer> books) {
        double total = 0;
        for (Map.Entry<Long, Integer> entry : books.entrySet()) {
            total += (bookRepository.findById(entry.getKey()).get().getPrice() * entry.getValue());
        }
        return total;
    }

    private void checkStock(Map<Long, Integer> books) {
        for (Map.Entry<Long, Integer> entry : books.entrySet()) {
            if (bookRepository.findById(entry.getKey()).get().getStockNumber() < entry.getValue()) {
                throw new OrderException("Out of stock", "ERR_O4");
            }
            if (entry.getValue() < 0) {
                throw new OrderException("You must buy more than 0 books", "ERR_O7");
            }
        }
    }

    private void updateStock(Map<Long, Integer> books) {
        for (Map.Entry<Long, Integer> entry : books.entrySet()) {
            Book book1 = bookRepository.findById(entry.getKey()).get();
            Integer boughtAmount = entry.getValue();
            Integer stockNumber = book1.getStockNumber();
            book1.setStockNumber(stockNumber - boughtAmount);
            bookRepository.save(book1);
        }
    }


    public void updateOrderStatus(UpdateOrderStatusRequest request) {
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new OrderException("Order does not exist", "ERR_O3"));
        if (OrderStatus.DELIVERED.equals(order.getOrderStatus()) || OrderStatus.CANCELED.equals(order.getOrderStatus())) {
            throw new OrderException("Order status can not change", "ERR_O5");
        } else {
            order.setOrderStatus(request.getStatus());
            if (OrderStatus.DELIVERED.equals(request.getStatus())) {
                order.setEndDate(LocalDateTime.now());
            }
            orderRepository.save(order);
        }
    }

    public List<Order> getOrdersBetweenDates(GetOrdersBetweenDatesRequest request) {
        List<Order> orderList = orderRepository.findOrdersByBetweenTwoDates(request.getStartDate(), request.getEndDate());
        if (orderList.isEmpty()) {
            throw new OrderException("Does not have order between two dates", "ERR_06");
        }
        return orderList;
    }

    private void validateParameters(CreateOrderRequest request) {
        List<String> fieldName = new ArrayList<>();
        if (request.getCustomerId() == null) {
            fieldName.add("customerId");
        }
        if (request.getBook().isEmpty()) {
            fieldName.add("book");
        }
        if (!fieldName.isEmpty()) {
            throw new OrderException("Missing parameters: " + Arrays.toString(fieldName.toArray()), "ERR_O8");
        }
    }
}
