package com.ecom.entities;

import javax.persistence.*;
import lombok.Data;

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
}
