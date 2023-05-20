package com.ecom.payload;

import com.ecom.entities.Customer;
import com.ecom.entities.ShippingType;
import com.ecom.entities.StateTax;
import lombok.Data;

@Data

public class OrderDetailDto {
    private long orderId;
    private String receiverName;
    private String address;
    private String city;
    private String dateOfPurchase;
}
