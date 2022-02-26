package com.example.readingisgood.controller;

import com.example.readingisgood.exception.BookException;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.types.requests.CreateBookRequest;
import com.example.readingisgood.types.responses.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public DefaultResponse createBook(@RequestBody CreateBookRequest request) {
        DefaultResponse defaultResponse = new DefaultResponse();
        bookService.createBook(request);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Book successfully created");
        return defaultResponse;
    }

    @GetMapping("/stock/{bookId}")
    public int getBookStock(@PathVariable Long bookId) {
        return bookService.getBookStock(bookId);
    }

    @PutMapping("/stock/{bookId}")
    public DefaultResponse addStock(@PathVariable Long bookId, @RequestParam(required = true) int number) throws BookException {
        DefaultResponse defaultResponse = new DefaultResponse();
        bookService.addStock(bookId, number);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Book stock successfully updated");
        return defaultResponse;
    }
}
