package com.example.readingisgood.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import com.example.readingisgood.exception.BookException;
import com.example.readingisgood.exception.StatisticsException;
import com.example.readingisgood.model.Order;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.repository.UserRepository;
import com.example.readingisgood.security.JwtUtils;
import com.example.readingisgood.types.responses.MonthlyOrderStatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    public MonthlyOrderStatisticsResponse getMonthlyOrderStaticsByUserId(Long userId, int month, String token) {
        userRepository.findById(userId).orElseThrow(() -> new StatisticsException("User does not exist", "ERR_S1"));
        checkRole(token);
        MonthlyOrderStatisticsResponse response = new MonthlyOrderStatisticsResponse();
        List<Order> orderList;
        switch (month) {

            case 1:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 1, 1, 0, 0),
                    LocalDateTime.of(2022, 1, 31, 0, 0));
                response.setMonth(Month.JANUARY);
                break;
            case 2:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 2, 1, 0, 0),
                    LocalDateTime.of(2022, 2, 28, 0, 0));
                response.setMonth(Month.FEBRUARY);
                break;
            case 3:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 3, 1, 0, 0),
                    LocalDateTime.of(2022, 3, 31, 0, 0));
                response.setMonth(Month.MARCH);
                break;
            case 4:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 4, 1, 0, 0),
                    LocalDateTime.of(2022, 4, 30, 0, 0));
                response.setMonth(Month.APRIL);
                break;
            case 5:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 5, 1, 0, 0),
                    LocalDateTime.of(2022, 5, 31, 0, 0));
                response.setMonth(Month.MAY);
                break;
            case 6:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 6, 1, 0, 0),
                    LocalDateTime.of(2022, 6, 30, 0, 0));
                response.setMonth(Month.JUNE);
                break;
            case 7:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 7, 1, 0, 0),
                    LocalDateTime.of(2022, 7, 31, 0, 0));
                response.setMonth(Month.JULY);
                break;
            case 8:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 8, 1, 0, 0),
                    LocalDateTime.of(2022, 8, 31, 0, 0));
                response.setMonth(Month.AUGUST);
                break;
            case 9:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 9, 1, 0, 0),
                    LocalDateTime.of(2022, 9, 30, 0, 0));
                response.setMonth(Month.SEPTEMBER);
                break;
            case 10:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 10, 1, 0, 0),
                    LocalDateTime.of(2022, 10, 31, 0, 0));
                response.setMonth(Month.OCTOBER);
                break;
            case 11:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 11, 1, 0, 0),
                    LocalDateTime.of(2022, 11, 30, 0, 0));
                response.setMonth(Month.NOVEMBER);
                break;
            case 12:
                orderList = orderRepository.findByUserIdAndDateBetween(userId, LocalDateTime.of(2022, 12, 1, 0, 0),
                    LocalDateTime.of(2022, 12, 31, 0, 0));
                response.setMonth(Month.DECEMBER);
                break;
            default:
                throw new StatisticsException("Please enter valid month", "ERR_S2");
        }
        if (orderList.isEmpty()) {
            throw new StatisticsException("User does not have order in given month", "ERR_S3");
        }
        int totalOrderCount = 0;
        int totalBookCount = 0;
        double totalPurchasedAmount = 0;
        for (Order order : orderList) {
            totalOrderCount += 1;
            totalPurchasedAmount += order.getTotalAmount();
            for (Integer value : order.getBook().values()) {
                totalBookCount += value;
            }
        }
        response.setTotalOrder(totalOrderCount);
        response.setTotalAmount(totalPurchasedAmount);
        response.setTotalBook(totalBookCount);
        return response;
    }

    private void checkRole(String token) {
        String mail = jwtUtils.getMail(token.substring(7));
        if (!userRepository.findUserByMail(mail).getRoles().contains("2")) {
            throw new BookException("You don't have permission", "ERR_B4");
        }
    }

}
