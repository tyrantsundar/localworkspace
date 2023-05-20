package com.ecom.payload;

import lombok.Data;

@Data
public class ShippingTypeDto {
    private String type;
    private double price;
    private int approxDeliveryDays;
}
