package com.ecom.controller;

import com.ecom.entities.Customer;
import com.ecom.payload.CustomerDto;
import com.ecom.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CustomerDto> getCustmerById(@PathVariable(value = "userId") long userId){
        return new ResponseEntity<>(customerService.getCustomer(userId),HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable(value = "userId")long userId){
        customerService.deleteCustomer(userId);
        return new ResponseEntity<>("Customer Deleted Successfully",HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<CustomerDto> updateCustomerById(@RequestBody CustomerDto customerDto,@PathVariable(value = "userId")long userId){
        return new ResponseEntity<>(customerService.updateCustomer(userId,customerDto),HttpStatus.OK);
    }

}
