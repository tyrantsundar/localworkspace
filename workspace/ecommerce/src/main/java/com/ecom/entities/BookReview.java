package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_reviews")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    private String reviews;
    private String rating;
    private String reviewDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId",nullable = false)
    private Book book;
    private String userName;
}
