package com.example.readingisgood.service;

import java.util.Objects;
import com.example.readingisgood.exception.BookException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.types.requests.CreateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public void createBook(CreateBookRequest request) {
        Book book = bookRepository.findByIsbnNumber(request.getIsbnNumber());
        if (Objects.nonNull(book)) {
            throw new BookException("Book exist", "ERR_B1");
        }
        Book newBook = Book.builder().id(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME))
            .isbnNumber(request.getIsbnNumber())
            .name(request.getName())
            .author(request.getAuthor())
            .publicationDate(request.getPublicationDate())
            .totalPage(request.getTotalPage())
            .stockNumber(request.getStockNumber())
            .type(request.getType())
            .price(request.getPrice())
            .build();
        bookRepository.save(newBook);
    }

    public void addBookStock(Long bookId, int number) throws BookException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookException("Book does not exist", "ERR_B2"));
        int stockNumber = book.getStockNumber();
        book.setStockNumber(stockNumber + number);
        bookRepository.save(book);
    }

    public int getBookStock(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookException("Book does not exist", "ERR_B2"));
        return book.getStockNumber();
    }
}
