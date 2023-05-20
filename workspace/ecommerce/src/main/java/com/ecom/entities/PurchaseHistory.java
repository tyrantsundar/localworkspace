package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;
    @JoinColumn(name = "userId",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    private String dateOfPurchase;
    @JoinColumn(name = "orderId",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderDetail orderDetail;
    private int quantity;
    private double price;
}
