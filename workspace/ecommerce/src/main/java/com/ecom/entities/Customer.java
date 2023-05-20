package com.ecom.entities;

import javax.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name = "firstName",nullable = false)
    private String firstName;
    @Column(name = "lastName",nullable = false)
    private String lastName;
    @Column(name = "address",nullable = false)
    private String address;
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name = "state",nullable = false)
    private String state;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "mobile",unique = true,nullable = false)
    private String mobile;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<OrderDetail> orderDetailSet = new HashSet<>();
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CreditCardDetail> creditCardDetailSet = new HashSet<>();
//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
//    private Set<BookReview> bookReviewSet = new HashSet<>();
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<PurchaseHistory> purchaseHistorySet = new HashSet<>();
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ShoppingCartItem> shoppingCartItemSet = new HashSet<>();
}
