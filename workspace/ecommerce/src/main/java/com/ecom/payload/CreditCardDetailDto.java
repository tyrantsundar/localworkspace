package com.ecom.payload;

import com.ecom.entities.Customer;
import lombok.Data;
@Data
public class CreditCardDetailDto {
    private String userName;
    private String creditCardNumber;
    private String cardType;
    private String cvvNumber;
    private String expiryDate;
}
