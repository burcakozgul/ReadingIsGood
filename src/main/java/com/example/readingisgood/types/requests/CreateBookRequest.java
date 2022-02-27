package com.example.readingisgood.types.requests;

import java.time.LocalDateTime;
import com.example.readingisgood.types.BookType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    private String isbnNumber;
    private String name;
    private String author;
    private int totalPage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publicationDate;
    private int stockNumber;
    private BookType type;
    private double price;
}
