package com.ecom.payload;

import lombok.Data;

@Data
public class BooksDto {
    private long inventoryId;
    private String name;
    private String author;
    private int nr_books;
    private double price;
}
