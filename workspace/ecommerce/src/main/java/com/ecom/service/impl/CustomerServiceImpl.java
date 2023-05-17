package com.ecom.service.impl;

import com.ecom.entities.Customer;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CustomerDto;
import com.ecom.repository.CustomerRepository;
import com.ecom.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    private Customer mapToCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setCity(customerDto.getCity());
        customer.setAddress(customerDto.getAddress());
        customer.setMobile(customerDto.getMobile());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setState(customerDto.getState());
        return customer;
    }

    private CustomerDto mapToCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUserId(customer.getUserId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setCity(customer.getCity());
        customerDto.setAddress(customer.getAddress());
        customerDto.setMobile(customer.getMobile());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());
        customerDto.setState(customer.getState());
        return customerDto;
    }

    private Customer updateCustomer(CustomerDto customerDto,Customer customer){
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setCity(customerDto.getCity());
        customer.setAddress(customerDto.getAddress());
        customer.setMobile(customerDto.getMobile());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setState(customerDto.getState());
        return customer;
    }


    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToCustomer(customerDto);
        Customer customerEntity = customerRepository.save(customer);
        CustomerDto newCustomerDto = mapToCustomerDto(customerEntity);
        return newCustomerDto;
    }

    @Override
    public void deleteCustomer(long userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDto updateCustomer(long userId, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        customer = updateCustomer(customerDto,customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return mapToCustomerDto(updatedCustomer);
    }

    @Override
    public CustomerDto getCustomer(long userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        return mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = customers.stream().map(c->mapToCustomerDto(c)).collect(Collectors.toList());
//        List<CustomerDto> customerDtos = new ArrayList<>();
//        for (Customer customer:customers){
//            customerDtos.add(mapToCustomerDto(customer));
//        }
        return customerDtos;
    }
}
