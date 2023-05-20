package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "shipping_type")
public class ShippingType {
    @Id
    private String type;
    private double price;
    @Column(name="approx_delivery_days")
    private int approxDeliveryDays;
    @OneToMany(mappedBy = "shippingType",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<OrderDetail> orderDetailSet = new HashSet<>();
}
