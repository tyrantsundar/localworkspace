package com.ecom.payload;

import lombok.Data;

@Data
public class StateTaxDto {
    private String stateName;
    private double salesTaxRate;
}
