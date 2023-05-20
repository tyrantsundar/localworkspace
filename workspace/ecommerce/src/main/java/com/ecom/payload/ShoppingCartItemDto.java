package com.ecom.payload;

import lombok.Data;
@Data
public class ShoppingCartItemDto {
    private long shoppingCartId;
    private double price;
    private String date;
    private int quantity;
}
