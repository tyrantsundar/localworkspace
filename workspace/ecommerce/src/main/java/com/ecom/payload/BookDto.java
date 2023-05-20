package com.ecom.payload;

import lombok.Data;

@Data
public class BookDto {
    private long bookId;
    private String name;
    private String author;
    private int nr_books;
    private double price;
}
