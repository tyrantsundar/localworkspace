package com.ecom.service.impl;

import com.ecom.entities.CreditCardDetail;
import com.ecom.entities.Customer;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.CreditCardDetailDto;
import com.ecom.repository.CreditCardDetailRepository;
import com.ecom.repository.CustomerRepository;
import com.ecom.service.CreditCardDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CreditCardDetailServiceImpl implements CreditCardDetailService {
    private CreditCardDetailRepository creditCardDetailRepository;
    private CustomerRepository customerRepository;

    public CreditCardDetailServiceImpl(CreditCardDetailRepository creditCardDetailRepository, CustomerRepository customerRepository) {
        this.creditCardDetailRepository = creditCardDetailRepository;
        this.customerRepository = customerRepository;
    }
    private CreditCardDetailDto mapToDto(CreditCardDetail creditCardDetail){
        CreditCardDetailDto creditCardDetailDto = new CreditCardDetailDto();
        creditCardDetailDto.setCardType(creditCardDetail.getCardType());
        creditCardDetailDto.setCreditCardNumber(creditCardDetail.getCreditCardNumber());
        creditCardDetailDto.setCvvNumber(creditCardDetail.getCvvNumber());
        creditCardDetailDto.setUserName(creditCardDetail.getUserName());
        creditCardDetailDto.setExpiryDate(creditCardDetail.getExpiryDate());
        return creditCardDetailDto;
    }
    private CreditCardDetail mapToEntity(CreditCardDetailDto creditCardDetailDto){
        CreditCardDetail creditCardDetailEntity = new CreditCardDetail();
        creditCardDetailEntity.setCardType(creditCardDetailDto.getCardType());
        creditCardDetailEntity.setCreditCardNumber(creditCardDetailDto.getCreditCardNumber());
        creditCardDetailEntity.setCvvNumber(creditCardDetailDto.getCvvNumber());
        creditCardDetailEntity.setUserName(creditCardDetailDto.getUserName());
        creditCardDetailEntity.setExpiryDate(creditCardDetailDto.getExpiryDate());
        return creditCardDetailEntity;
    }
    private CreditCardDetail updateEntity(CreditCardDetailDto creditCardDetailDto, CreditCardDetail creditCardDetailEntity){
        creditCardDetailEntity.setCardType(creditCardDetailDto.getCardType());
        creditCardDetailEntity.setCreditCardNumber(creditCardDetailDto.getCreditCardNumber());
        creditCardDetailEntity.setCvvNumber(creditCardDetailDto.getCvvNumber());
        creditCardDetailEntity.setUserName(creditCardDetailDto.getUserName());
        creditCardDetailEntity.setExpiryDate(creditCardDetailDto.getExpiryDate());
        return creditCardDetailEntity;
    }
    @Override
    public CreditCardDetailDto createCreditCard(CreditCardDetailDto creditCardDetailDto, long userId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        CreditCardDetail creditCardDetail = mapToEntity(creditCardDetailDto);
        creditCardDetail.setCustomer(customer);
        CreditCardDetail savedCreditCardDetail = creditCardDetailRepository.save(creditCardDetail);
        return mapToDto(savedCreditCardDetail);
    }

    @Override
    public CreditCardDetailDto getCreditCardDetailByUserName(long userId, String userName) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        CreditCardDetail creditCardDetail = creditCardDetailRepository.findById(userName).orElseThrow(
                () -> new ResourceNotFoundException("CreditCard", "userName", userName));
        return mapToDto(creditCardDetail);
    }

    @Override
    public List<CreditCardDetailDto> getAllCreditCardDetailsForUser(long userId) {
       return creditCardDetailRepository.findByCustomerUserId(userId).stream().map(cc->mapToDto(cc)).collect(Collectors.toList());
    }

    @Override
    public CreditCardDetailDto updateCreditCardDetail(long userId, String userName, CreditCardDetailDto creditCardDetailDto) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        CreditCardDetail creditCardDetail = creditCardDetailRepository.findById(userName).orElseThrow(
                () -> new ResourceNotFoundException("CreditCard", "userName", userName));
        creditCardDetail = updateEntity(creditCardDetailDto,creditCardDetail);
        return mapToDto(creditCardDetailRepository.save(creditCardDetail));
    }

    @Override
    public void deleteCreditCardDetail(long userId, String userName) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        creditCardDetailRepository.findById(userName).orElseThrow(
                () -> new ResourceNotFoundException("CreditCard", "userName", userName));
        creditCardDetailRepository.deleteById(userName);
    }
}
