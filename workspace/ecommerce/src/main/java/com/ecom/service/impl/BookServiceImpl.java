package com.ecom.service.impl;

import com.ecom.entities.Book;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.BooksDto;
import com.ecom.repository.BooksRepository;
import com.ecom.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BooksRepository booksRepository;

    public BookServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    private BooksDto mapBookToDto(Book book){
        BooksDto booksDto = new BooksDto();
        booksDto.setInventoryId(book.getInventoryId());
        booksDto.setName(book.getName());
        booksDto.setAuthor(book.getAuthor());
        booksDto.setNr_books(book.getNr_books());
        booksDto.setPrice(book.getPrice());
        return booksDto;
    }
    private Book mapDtoToBook(BooksDto booksDto){
        Book book = new Book();
        book.setInventoryId(booksDto.getInventoryId());
        book.setName(booksDto.getName());
        book.setAuthor(booksDto.getAuthor());
        book.setNr_books(booksDto.getNr_books());
        book.setPrice(booksDto.getPrice());
        return book;
    }
    private Book updateDtoToBook(BooksDto booksDto, Book book){
        book.setName(booksDto.getName());
        book.setAuthor(booksDto.getAuthor());
        book.setNr_books(booksDto.getNr_books());
        book.setPrice(booksDto.getPrice());
        return book;
    }
    @Override
    public List<BooksDto> getAllBooks() {
        return booksRepository.findAll().stream().map(book -> mapBookToDto(book)).collect(Collectors.toList());
    }

    @Override
    public BooksDto getBookById(long inventoryId) {
        Book book = booksRepository.findById(inventoryId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "inventoryId", inventoryId));
        return mapBookToDto(book);
    }

    @Override
    public BooksDto createBook(BooksDto booksDto) {
        Book bookToSave = mapDtoToBook(booksDto);
        Book savedBook = booksRepository.save(bookToSave);
        return mapBookToDto(savedBook);
    }

    @Override
    public BooksDto updateBook(long inventoryId, BooksDto booksDto) {
        Book book = booksRepository.findById(inventoryId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "inventoryId", inventoryId));
        Book updatedBook = booksRepository.save(updateDtoToBook(booksDto, book));
        return mapBookToDto(updatedBook);
    }

    @Override
    public void deleteBook(long inventoryId) {
        booksRepository.findById(inventoryId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "inventoryId", inventoryId));
        booksRepository.deleteById(inventoryId);
    }
}
