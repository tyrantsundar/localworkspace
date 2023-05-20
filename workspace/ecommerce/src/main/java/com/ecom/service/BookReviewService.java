package com.ecom.service;

import com.ecom.payload.BookReviewDto;

import java.util.List;

public interface BookReviewService {
    BookReviewDto createBookReview(BookReviewDto bookReviewDto,long bookId);
    BookReviewDto getBookReviewByReviewId(long reviewId, long bookId);
    List<BookReviewDto> getAllBookReviewByBookId(long bookId);
    BookReviewDto updateBookReview(long reviewId,long bookId, BookReviewDto bookReviewDto);
    void deleteBookReview(long reviewId, long bookId);
}
