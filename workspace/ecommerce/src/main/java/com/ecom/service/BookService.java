package com.ecom.service;

import com.ecom.payload.BooksDto;

import java.util.List;

public interface BookService {
    List<BooksDto> getAllBooks();
    BooksDto getBookById(long inventoryId);
    BooksDto createBook(BooksDto booksDto);
    BooksDto updateBook(long inventoryId,BooksDto booksDto);
    void deleteBook(long inventoryId);
}
