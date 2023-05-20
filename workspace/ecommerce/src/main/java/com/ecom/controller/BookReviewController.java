package com.ecom.controller;

import com.ecom.payload.BookReviewDto;
import com.ecom.service.BookReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookReview")
public class BookReviewController {
    private BookReviewService bookReviewService;

    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }
    @PostMapping("/{bookId}")
    public ResponseEntity<BookReviewDto> createBookReview(@RequestBody BookReviewDto bookReviewDto,
                                                          @PathVariable(value = "bookId") long bookId){
        return new ResponseEntity<>(bookReviewService.createBookReview(bookReviewDto,bookId), HttpStatus.CREATED);
    }
    @GetMapping("/{reviewId}/{bookId}")
    public ResponseEntity<BookReviewDto> getBookReview(@PathVariable(value = "reviewId") long reviewId,
                                                       @PathVariable(value = "bookId") long bookId){
        return new ResponseEntity<>(bookReviewService.getBookReviewByReviewId(reviewId, bookId),HttpStatus.OK);
    }
    @GetMapping("/{bookId}")
    public ResponseEntity<List<BookReviewDto>> getAllBookReviews(@PathVariable(value = "bookId") long bookId){
        return new ResponseEntity<>(bookReviewService.getAllBookReviewByBookId(bookId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}/{bookId}")
    public ResponseEntity<BookReviewDto> updateBookReview(@PathVariable(value = "reviewId") long reviewId,
                                                       @PathVariable(value = "bookId") long bookId, @RequestBody BookReviewDto bookReviewDto){
        return new ResponseEntity<>(bookReviewService.updateBookReview(reviewId, bookId, bookReviewDto),HttpStatus.OK);
    }
    @DeleteMapping("/{reviewId}/{bookId}")
    public ResponseEntity<String> deleteBookReview(@PathVariable(value = "reviewId") long reviewId,
                                                   @PathVariable(value = "bookId") long bookId){
        bookReviewService.deleteBookReview(reviewId,bookId);
        return new ResponseEntity<>("BookReview Deleted Successfully",HttpStatus.OK);
    }
}
