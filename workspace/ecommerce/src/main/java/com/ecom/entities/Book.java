package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "author",nullable = false)
    private String author;
    @Column(name = "nr_books",nullable = false)
    private int nr_books;
    @Column(name = "price",nullable = false)
    private double price;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<ShoppingCartItem> shoppingCartItemSet = new HashSet<>();
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<BookReview> bookReviewSet = new HashSet<>();
}
