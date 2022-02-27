package com.example.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.readingisgood.exception.OrderException;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.service.data.BookServiceTestData;
import com.example.readingisgood.service.data.CustomerServiceTestData;
import com.example.readingisgood.service.data.OrderServiceTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test createOrder. When customer not exist then throw exception.")
    void createOrder_When_CustomerNotExist_Then_Throw_OrderException() {
        String expectedResult = "Customer does not exist";
        when(customerRepository.findById(any())).thenThrow(new OrderException("Customer does not exist", "ERR_O1"));
        OrderException exception =
            assertThrows(OrderException.class, () -> orderService.createOrder(OrderServiceTestData.get_CreateOrderRequest()));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    @DisplayName("Test createOrder. When book not exist then throw exception.")
    void createOrder_When_BookNotExist_Then_Throw_OrderException() {
        String expectedResult = "Book does not exist";
        doReturn(CustomerServiceTestData.get_Customer()).when(customerRepository).findById(any());
        when(bookRepository.findById(any())).thenThrow(new OrderException("Book does not exist", "ERR_O2"));
        OrderException exception =
            assertThrows(OrderException.class, () -> orderService.createOrder(OrderServiceTestData.get_CreateOrderRequest()));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    @DisplayName("Test createOrder. When book not exist then throw exception.")
    void createOrder_When_ValidOrder_Then_Save() {
        doReturn(CustomerServiceTestData.get_Customer()).when(customerRepository).findById(any());
        doReturn(BookServiceTestData.get_Book()).when(bookRepository).findById(any());
        orderService.createOrder(OrderServiceTestData.get_CreateOrderRequest());

        verify(orderRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test updateOrderStatus. When status not valid then throw exception.")
    void updateOrderStatus_When_StatusNotValid_Throw_OrderException() {
        String expectedResult = "Order status can not change";
        doReturn(OrderServiceTestData.get_DeliveredOrder()).when(orderRepository).findById(any());
        OrderException exception =
            assertThrows(OrderException.class, () -> orderService.updateOrderStatus(OrderServiceTestData.get_UpdateOrderStatusRequest()));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    @DisplayName("Test updateOrderStatus. When status valid then save.")
    void updateOrderStatus_When_StatusValid_Then_Save() {
        doReturn(OrderServiceTestData.get_ShippedOrder()).when(orderRepository).findById(any());
        orderService.updateOrderStatus(OrderServiceTestData.get_UpdateOrderStatusRequest());

        verify(orderRepository, times(1)).save(any());
    }
}
