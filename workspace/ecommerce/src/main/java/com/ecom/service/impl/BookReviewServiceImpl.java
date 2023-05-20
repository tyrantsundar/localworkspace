package com.ecom.service.impl;

import com.ecom.entities.Book;
import com.ecom.entities.BookReview;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.BookReviewDto;
import com.ecom.repository.BookRepository;
import com.ecom.repository.BookReviewRepository;
import com.ecom.service.BookReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookReviewServiceImpl implements BookReviewService {
    private BookReviewRepository bookReviewRepository;
    private BookRepository bookRepository;

    public BookReviewServiceImpl(BookReviewRepository bookReviewRepository, BookRepository bookRepository) {
        this.bookReviewRepository = bookReviewRepository;
        this.bookRepository = bookRepository;
    }

    private BookReviewDto mapToDto(BookReview bookReview){
        BookReviewDto bookReviewDto = new BookReviewDto();
        bookReviewDto.setUserName(bookReview.getUserName());
        bookReviewDto.setRating(bookReview.getRating());
        bookReviewDto.setReviewId(bookReview.getReviewId());
        bookReviewDto.setReviews(bookReview.getReviews());
        bookReviewDto.setReviewDate(bookReview.getReviewDate());
        return bookReviewDto;
    }
    private BookReview mapToEntity(BookReviewDto bookReviewDto){
        BookReview bookReview = new BookReview();
        bookReview.setUserName(bookReviewDto.getUserName());
        bookReview.setRating(bookReviewDto.getRating());
        bookReview.setReviewId(bookReviewDto.getReviewId());
        bookReview.setReviews(bookReviewDto.getReviews());
        bookReview.setReviewDate(bookReviewDto.getReviewDate());
        return bookReview;
    }
    private BookReview updateEntity(BookReviewDto bookReviewDto,BookReview bookReview){
        bookReview.setUserName(bookReviewDto.getUserName());
        bookReview.setRating(bookReviewDto.getRating());
//        bookReview.setReviewId(bookReviewDto.getReviewId());
        bookReview.setReviews(bookReviewDto.getReviews());
        bookReview.setReviewDate(bookReviewDto.getReviewDate());
        return bookReview;
    }
    @Override
    public BookReviewDto createBookReview(BookReviewDto bookReviewDto, long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "BookId", bookId));
        BookReview bookReview = mapToEntity(bookReviewDto);
        bookReview.setBook(book);
        BookReview savedReview = bookReviewRepository.save(bookReview);
        return mapToDto(savedReview);
    }

    @Override
    public BookReviewDto getBookReviewByReviewId(long reviewId, long bookId) {
        bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "BookId", bookId));
        BookReview bookReview = bookReviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFoundException("BookReview", "ReviewId", reviewId));
        return mapToDto(bookReview);
    }

    @Override
    public List<BookReviewDto> getAllBookReviewByBookId(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "BookId", bookId));
        List<BookReview> bookReviews = bookReviewRepository.findByBookBookId(bookId);
        return bookReviews.stream().map(br->mapToDto(br)).collect(Collectors.toList());
    }

    @Override
    public BookReviewDto updateBookReview(long reviewId, long bookId, BookReviewDto bookReviewDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "BookId", bookId));
        BookReview bookReview = bookReviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFoundException("BookReview", "ReviewId", reviewId));
        bookReview = updateEntity(bookReviewDto,bookReview);
        bookReview.setBook(book);
        return mapToDto(bookReviewRepository.save(bookReview));
    }

    @Override
    public void deleteBookReview(long reviewId, long bookId) {
        bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "BookId", bookId));
        bookReviewRepository.findById(reviewId).orElseThrow(
                () -> new ResourceNotFoundException("BookReview", "ReviewId", reviewId));
        bookReviewRepository.deleteById(reviewId);
    }
}
