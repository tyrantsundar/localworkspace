package com.ecom.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="state_taxes")
public class StateTax {
    @Id
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "sales_tax_rate")
    private double salesTaxRate;
    @OneToMany(mappedBy = "state",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<OrderDetail> orderDetailSet = new HashSet<>();
}
