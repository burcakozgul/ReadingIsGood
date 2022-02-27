package com.example.readingisgood.service.data;

import java.time.LocalDateTime;
import java.util.Optional;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.types.BookType;
import com.example.readingisgood.types.requests.CreateBookRequest;

public class BookServiceTestData {

    public static CreateBookRequest get_CreateBookRequest() {
        CreateBookRequest request = CreateBookRequest.builder()
            .isbnNumber("9781501171345")
            .author("Laura Dave")
            .name("The Last Thing He Told Me")
            .totalPage(320)
            .stockNumber(17)
            .price(20.50)
            .publicationDate(LocalDateTime.of(2020, 1, 1, 0, 0))
            .type(BookType.ACTION).build();
        return request;
    }

    public static Optional<Book> get_Book() {
        Optional<Book> book = Optional.ofNullable(Book.builder().id(1L)
            .isbnNumber("9781501171345")
            .author("Laura Dave")
            .name("The Last Thing He Told Me")
            .totalPage(320)
            .stockNumber(17)
            .price(20.50)
            .publicationDate(LocalDateTime.of(2020, 1, 1, 0, 0))
            .type(BookType.ACTION).build());
        return book;
    }
}
