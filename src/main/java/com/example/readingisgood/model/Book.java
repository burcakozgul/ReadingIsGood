package com.example.readingisgood.model;

import java.time.LocalDateTime;
import com.example.readingisgood.types.BookType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";
    @Id
    private Long id;
    private String isbnNumber;
    private String name;
    private String author;
    private int totalPage;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publicationDate;
    private Integer stockNumber;
    private BookType type;
    private double price;
    @Version
    private Long version;
}
