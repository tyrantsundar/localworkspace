package com.ecom.controller;

import com.ecom.payload.BookDto;
import com.ecom.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(value ="bookId" ) long bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId),HttpStatus.OK);
    }
    @PutMapping("/{bookId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(value = "bookId") long bookId
                                                , @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.updateBook(bookId, bookDto),HttpStatus.OK);
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "bookId")long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book is deleted",HttpStatus.OK);
    }
}
