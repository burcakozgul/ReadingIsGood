package com.example.readingisgood.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.example.readingisgood.exception.BookException;
import com.example.readingisgood.model.Book;
import com.example.readingisgood.repository.BookRepository;
import com.example.readingisgood.repository.UserRepository;
import com.example.readingisgood.security.JwtUtils;
import com.example.readingisgood.types.requests.CreateBookRequest;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public void createBook(CreateBookRequest request, String token) {
        validateParameters(request);
        checkRole(token);
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

    public void addBookStock(Long bookId, int number, String token) throws BookException {
        checkRole(token);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookException("Book does not exist", "ERR_B2"));
        Integer stockNumber = book.getStockNumber();
        book.setStockNumber(stockNumber + number);
        bookRepository.save(book);
    }

    public int getBookStock(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookException("Book does not exist", "ERR_B2"));
        return book.getStockNumber();
    }

    private void checkRole(String token) {
        String mail = jwtUtils.getMail(token.substring(7));
        if (!userRepository.findUserByMail(mail).getRoles().contains("2")){
            throw new BookException("You don't have permission", "ERR_B4");
        }
    }

    private void validateParameters(CreateBookRequest request) {
        List<String> fieldName = new ArrayList<>();
        if (Strings.isNullOrEmpty(request.getIsbnNumber())) {
            fieldName.add("isbnNumber");
        }
        if (request.getStockNumber() == null) {
            fieldName.add("stockNumber");
        }
        if (Strings.isNullOrEmpty(request.getName())) {
            fieldName.add("name");
        }
        if (Strings.isNullOrEmpty(request.getAuthor())) {
            fieldName.add("author");
        }
        if (!fieldName.isEmpty()) {
            throw new BookException("Missing parameters: " + Arrays.toString(fieldName.toArray()), "ERR_B3");
        }
    }
}
