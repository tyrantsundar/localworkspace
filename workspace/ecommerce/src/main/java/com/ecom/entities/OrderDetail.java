package com.ecom.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false)
    Customer customer;
    private String receiverName;
    private String address;
    private String city;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_name",nullable = false)
    private StateTax state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type",nullable = false)
    private ShippingType shippingType;
    @Column(name = "date_of_purchase")
    private String dateOfPurchase;
    @OneToMany(mappedBy = "orderDetail",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<PurchaseHistory> purchaseHistorySet = new HashSet<>();
}
