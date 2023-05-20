package com.ecom.payload;

import com.ecom.entities.Customer;
import com.ecom.entities.OrderDetail;
import lombok.Data;

@Data

public class PurchaseHistoryDto {
    private long purchaseId;
    private String dateOfPurchase;
    private int quantity;
    private double price;
}
