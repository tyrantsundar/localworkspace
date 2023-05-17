package com.ecom.service;


import com.ecom.payload.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customer);
    void deleteCustomer(long userId);
    CustomerDto updateCustomer(long userId,CustomerDto customer);
    CustomerDto getCustomer(long userId);
    List<CustomerDto> getAllCustomers();
}
