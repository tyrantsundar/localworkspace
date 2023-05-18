package com.ecom.controller;

import com.ecom.payload.BooksDto;
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
    public ResponseEntity<BooksDto> createBook(@RequestBody BooksDto booksDto){
        return new ResponseEntity<>(bookService.createBook(booksDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<BooksDto>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }
    @GetMapping("/{inventoryId}")
    public ResponseEntity<BooksDto> getBookById(@PathVariable(value ="inventoryId" ) long inventoryId){
        return new ResponseEntity<>(bookService.getBookById(inventoryId),HttpStatus.OK);
    }
    @PutMapping("/{inventoryId}")
    public ResponseEntity<BooksDto> updateBook(@PathVariable(value = "inventoryId") long inventoryId
                                                ,@RequestBody BooksDto booksDto){
        return new ResponseEntity<>(bookService.updateBook(inventoryId,booksDto),HttpStatus.OK);
    }
    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "inventoryId")long inventoryId){
        bookService.deleteBook(inventoryId);
        return new ResponseEntity<>("Book is deleted",HttpStatus.OK);
    }
}
