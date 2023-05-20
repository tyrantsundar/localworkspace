package com.ecom.service;

import com.ecom.payload.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(long bookId);
    BookDto createBook(BookDto bookDto);
    BookDto updateBook(long bookId, BookDto bookDto);
    void deleteBook(long bookId);
}
