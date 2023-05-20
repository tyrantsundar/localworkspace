package com.ecom.service;

import com.ecom.payload.ShippingTypeDto;

import java.util.List;

public interface ShippingTypeService {
    ShippingTypeDto createShippingType(ShippingTypeDto shippingTypeDto);
    ShippingTypeDto getShippingTypeById(String type);
    List<ShippingTypeDto> getAllShippingType();
    ShippingTypeDto updateShippingType(String type, ShippingTypeDto shippingTypeDto);
    void deleteShippingType(String type);
}
