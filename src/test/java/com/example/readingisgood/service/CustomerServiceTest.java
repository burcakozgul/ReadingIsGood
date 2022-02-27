package com.example.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.readingisgood.exception.CustomerException;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.service.data.CustomerServiceTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    SequenceGeneratorService sequenceGeneratorService;
    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test createCustomer. When customer exist then throw exception.")
    void createCustomer_When_CustomerExist_Then_Throw_CustomerException() {
        String expectedResult = "Customer exist";
        doReturn(new Customer()).when(customerRepository).findCustomerByMail(any());
        CustomerException exception =
            assertThrows(CustomerException.class, () -> customerService.createCustomer(CustomerServiceTestData.get_CreateCustomerRequest()));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    @DisplayName("Test createCustomer. When customer not exist then save.")
    void createCustomer_When_CustomerNotExist_Then_Save() {
        doReturn(null).when(customerRepository).findCustomerByMail(any());
        customerService.createCustomer(CustomerServiceTestData.get_CreateCustomerRequest());

        verify(customerRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test getAllOrdersByCustomerId. When customer not exist then throw exception.")
    void getAllOrdersByCustomerId_When_CustomerNotExist_Then_Throw_CustomerException() {
        String expected = "Customer does not exist";
        when(customerRepository.findCustomerByMail(any())).thenThrow(new CustomerException("Customer does not exist", "ERR_C2"));
        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.getAllOrdersByCustomerId(1L, 2, 4));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("Test getAllOrdersByCustomerId. When customer not exist then throw exception.")
    void getAllOrdersByCustomerId_When_OrderNotExist_Then_Throw_CustomerException() {
        String expected = "Customer does not have order";
        doReturn(CustomerServiceTestData.get_Customer()).when(customerRepository).findById(any());
        doReturn(Page.empty()).when(orderRepository).findOrdersByCustomerId(any(), any());
        CustomerException exception = assertThrows(CustomerException.class, () -> customerService.getAllOrdersByCustomerId(1L, 2, 4));

        assertEquals(expected, exception.getMessage());
    }

}
