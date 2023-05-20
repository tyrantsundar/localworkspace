package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shoppingCartId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId",nullable = false)
    private Book book;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "date",nullable = false)
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false)
    private Customer customer;
    @Column(name = "quantity",nullable = false)
    private int quantity;
}
