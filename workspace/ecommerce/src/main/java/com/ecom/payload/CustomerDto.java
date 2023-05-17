package com.ecom.payload;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;
@Data
public class CustomerDto {

    private long userId;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String email;
    private String mobile;
}
