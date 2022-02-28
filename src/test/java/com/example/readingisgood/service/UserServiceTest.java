package com.example.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.readingisgood.exception.UserException;
import com.example.readingisgood.model.User;
import com.example.readingisgood.repository.UserRepository;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.service.data.UserServiceTestData;
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
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
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
    @DisplayName("Test createUser. When user exist then throw exception.")
    void createUser_When_UserExist_Then_Throw_UserException() {
        String expectedResult = "User exist";
        doReturn(new User()).when(userRepository).findUserByMail(any());
        UserException exception =
            assertThrows(UserException.class, () -> userService.createUser(UserServiceTestData.get_CreateUserRequest()));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    @DisplayName("Test createUser. When user not exist then save.")
    void createUser_When_UserNotExist_Then_Save() {
        doReturn(null).when(userRepository).findUserByMail(any());
        userService.createUser(UserServiceTestData.get_CreateUserRequest());

        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test getAllOrdersByUserId. When user not exist then throw exception.")
    void getAllOrdersByUserId_When_UserNotExist_Then_Throw_UserException() {
        String expected = "User does not exist";
        when(userRepository.findUserByMail(any())).thenThrow(new UserException("User does not exist", "ERR_C2"));
        UserException exception = assertThrows(UserException.class, () -> userService.getAllOrdersByUserId(1L, 2, 4));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("Test getAllOrdersByUserId. When order not exist then throw exception.")
    void getAllOrdersByUserId_When_OrderNotExist_Then_Throw_UserException() {
        String expected = "User does not have order";
        doReturn(UserServiceTestData.get_User()).when(userRepository).findById(any());
        doReturn(Page.empty()).when(orderRepository).findOrdersByUserId(any(), any());
        UserException exception = assertThrows(UserException.class, () -> userService.getAllOrdersByUserId(1L, 2, 4));

        assertEquals(expected, exception.getMessage());
    }

}
