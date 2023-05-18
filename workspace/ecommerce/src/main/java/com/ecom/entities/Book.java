package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryId;
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "author",nullable = false)
    private String author;
    @Column(name = "nr_books",nullable = false)
    private int nr_books;
    @Column(name = "price",nullable = false)
    private double price;
}
