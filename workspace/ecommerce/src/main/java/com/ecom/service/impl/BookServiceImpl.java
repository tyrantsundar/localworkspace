package com.ecom.service.impl;

import com.ecom.entities.Book;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.BookDto;
import com.ecom.repository.BookRepository;
import com.ecom.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private BookDto mapBookToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setNr_books(book.getNr_books());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }
    private Book mapDtoToBook(BookDto bookDto){
        Book book = new Book();
        book.setBookId(bookDto.getBookId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setNr_books(bookDto.getNr_books());
        book.setPrice(bookDto.getPrice());
        return book;
    }
    private Book updateDtoToBook(BookDto bookDto, Book book){
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setNr_books(bookDto.getNr_books());
        book.setPrice(bookDto.getPrice());
        return book;
    }
    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> mapBookToDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "bookId", bookId));
        return mapBookToDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book bookToSave = mapDtoToBook(bookDto);
        Book savedBook = bookRepository.save(bookToSave);
        return mapBookToDto(savedBook);
    }

    @Override
    public BookDto updateBook(long bookId, BookDto bookDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "bookId", bookId));
        Book updatedBook = bookRepository.save(updateDtoToBook(bookDto, book));
        return mapBookToDto(updatedBook);
    }

    @Override
    public void deleteBook(long bookId) {
        bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "bookId", bookId));
        bookRepository.deleteById(bookId);
    }
}
