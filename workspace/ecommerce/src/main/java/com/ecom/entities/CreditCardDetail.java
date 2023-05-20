package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "credit_card_details")
public class CreditCardDetail {
    @Id
    private String userName;
    private String creditCardNumber;
    private String cardType;
    private String cvvNumber;
    private String expiryDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false)
    private Customer customer;
}
