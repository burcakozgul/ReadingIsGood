package com.example.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.readingisgood.exception.BookException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.repository.UserRepository;
import com.example.readingisgood.security.JwtUtils;
import com.example.readingisgood.service.data.BookServiceTestData;
import com.example.readingisgood.service.data.UserServiceTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @Mock
    JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test createBook. When book exist then throw exception.")
    void createBook_When_BookExist_Then_Throw_BookException() {
        String expectedResult = "Book exist";
        doReturn(new Book()).when(bookRepository).findByIsbnNumber(any());
        doReturn(UserServiceTestData.get_User2()).when(userRepository).findUserByMail(any());
        BookException exception = assertThrows(BookException.class, () -> bookService.createBook(BookServiceTestData.get_CreateBookRequest(),"efesfe3243432r42" ));

        assertEquals(expectedResult, exception.getMessage());
    }

    @Test
    @DisplayName("Test createBook. When book not exist then save.")
    void createBook_When_BookNotExist_Then_Save() {
        doReturn(null).when(bookRepository).findByIsbnNumber(any());
        doReturn(UserServiceTestData.get_User2()).when(userRepository).findUserByMail(any());
        bookService.createBook(BookServiceTestData.get_CreateBookRequest(), "efesfe3243432r42");

        verify(bookRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test addBookStock. When book not exist then throw exception.")
    void addBookStock_When_BookNotExist_Then_Throw_BookException() {
        String expected = "Book does not exist";
        doReturn(UserServiceTestData.get_User2()).when(userRepository).findUserByMail(any());
        when(bookRepository.findById(any())).thenThrow(new BookException("Book does not exist", "ERR_B2"));
        BookException exception = assertThrows(BookException.class, () -> bookService.addBookStock(1L, 6, "efesfe3243432r42"));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("Test addBookStock. When book exist then save.")
    void addBookStock_When_BookExist_Then_Save() {
        doReturn(UserServiceTestData.get_User2()).when(userRepository).findUserByMail(any());
        doReturn(BookServiceTestData.get_Book()).when(bookRepository).findById(any());
        bookService.addBookStock(1L, 5,"efesfe3243432r42");

        verify(bookRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test getBookStock. When book not exist then throw exception.")
    void getBookStock_When_BookNotExist_Then_Throw_BookException() {
        String expected = "Book does not exist";
        when(bookRepository.findById(any())).thenThrow(new BookException("Book does not exist", "ERR_B2"));
        BookException exception = assertThrows(BookException.class, () -> bookService.getBookStock(1L));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    @DisplayName("Test getBookStock. When book exist then return stockNumber.")
    void getBookStock_When_BookExist_Then_Return_StockNumber() {
        doReturn(BookServiceTestData.get_Book()).when(bookRepository).findById(any());
        int actual = bookService.getBookStock(1L);

        assertEquals(17, actual);
    }


}
