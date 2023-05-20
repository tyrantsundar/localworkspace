package com.ecom.service;

import com.ecom.entities.CreditCardDetail;
import com.ecom.payload.CreditCardDetailDto;

import java.util.List;

public interface CreditCardDetailService {
    CreditCardDetailDto createCreditCard(CreditCardDetailDto creditCardDetailDto, long userId);
    CreditCardDetailDto getCreditCardDetailByUserName(long userId, String userName);
    List<CreditCardDetailDto> getAllCreditCardDetailsForUser(long userId);
    CreditCardDetailDto updateCreditCardDetail(long userId,String userName,CreditCardDetailDto creditCardDetailDto);
    void deleteCreditCardDetail(long userId, String userName);
}
