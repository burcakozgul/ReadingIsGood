package com.example.readingisgood.controller;

import com.example.readingisgood.exception.BookException;
import com.example.readingisgood.service.BookService;
import com.example.readingisgood.types.requests.CreateBookRequest;
import com.example.readingisgood.types.responses.DefaultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Api(value = "Book Controller Class")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    @ApiOperation(value = "New Book creating method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public DefaultResponse createBook(@RequestBody CreateBookRequest request, @RequestHeader("Authorization") String token) {
        DefaultResponse defaultResponse = new DefaultResponse();
        bookService.createBook(request, token);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Book successfully created");
        return defaultResponse;
    }

    @GetMapping("/stock/{bookId}")
    @ApiOperation(value = "Get book stock by bookId method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public int getBookStock(@PathVariable Long bookId) {
        return bookService.getBookStock(bookId);
    }

    @PutMapping("/stock/{bookId}")
    @ApiOperation(value = "Add book stock by bookId method")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public DefaultResponse addBookStock(@PathVariable Long bookId, @RequestParam(required = true) int number,
                                        @RequestHeader("Authorization") String token) throws BookException {
        DefaultResponse defaultResponse = new DefaultResponse();
        bookService.addBookStock(bookId, number, token);
        defaultResponse.setSuccess(true);
        defaultResponse.setMessage("Book stock successfully updated");
        return defaultResponse;
    }
}
