package com.ecom.repository;

import com.ecom.entities.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReview,Long> {
    List<BookReview> findByBookBookId(long bookId);
}
