package com.ecom.controller;

import com.ecom.payload.CreditCardDetailDto;
import com.ecom.service.CreditCardDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/creditCard")
public class CreditCardController {
    private CreditCardDetailService creditCardDetailService;

    public CreditCardController(CreditCardDetailService creditCardDetailService) {
        this.creditCardDetailService = creditCardDetailService;
    }
    @PostMapping("/{userId}")
    public ResponseEntity<CreditCardDetailDto> createCreditCard(@RequestBody CreditCardDetailDto creditCardDetailDto
                                              ,@PathVariable(value = "userId")long userId){
       return new ResponseEntity<>(creditCardDetailService.createCreditCard(creditCardDetailDto,userId), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/{userName}")
    public ResponseEntity<CreditCardDetailDto> getCreditCardByName(@PathVariable(value = "userId")long userId
                                            , @PathVariable(value = "userName")String userName){
        return new ResponseEntity<>(creditCardDetailService.getCreditCardDetailByUserName(userId,userName),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<CreditCardDetailDto>> getAllCreditCardDetails(@PathVariable(value = "userId")long userId){
        return new ResponseEntity<>(creditCardDetailService.getAllCreditCardDetailsForUser(userId),HttpStatus.OK);
    }
    @PutMapping("/{userId}/{userName}")
    public ResponseEntity<CreditCardDetailDto> updateCreditCardDetail(@PathVariable(value = "userId")long userId
            , @PathVariable(value = "userName")String userName, @RequestBody CreditCardDetailDto creditCardDetailDto){
        return new ResponseEntity<>(creditCardDetailService.updateCreditCardDetail(userId,userName,creditCardDetailDto),HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/{userName}")
    public ResponseEntity<String> deleteCreditCardDetail(@PathVariable(value = "userId")long userId
            , @PathVariable(value = "userName")String userName){
        creditCardDetailService.deleteCreditCardDetail(userId,userName);
        return new ResponseEntity<>("CreditCard deleted successfully",HttpStatus.OK);
    }
}
