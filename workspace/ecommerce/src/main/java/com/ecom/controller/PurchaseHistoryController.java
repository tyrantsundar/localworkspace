package com.ecom.controller;

import com.ecom.payload.PurchaseHistoryDto;
import com.ecom.service.PurchaseHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/purchaseHistory")
public class PurchaseHistoryController {
    private PurchaseHistoryService purchaseHistoryService;

    public PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService) {
        this.purchaseHistoryService = purchaseHistoryService;
    }
    @PostMapping("/{userId}/{orderId}")
    public ResponseEntity<PurchaseHistoryDto> createPurchaseHistory(@PathVariable(value="userId")long userId
            , @PathVariable(value="orderId")long orderId, @RequestBody PurchaseHistoryDto purchaseHistoryDto){
        return new ResponseEntity<>(purchaseHistoryService.createPurchaseHistory(purchaseHistoryDto,userId,orderId), HttpStatus.CREATED);
    }
    @GetMapping("/{purchaseId}/{userId}/{orderId}")
    public ResponseEntity<PurchaseHistoryDto> getPurchaseHistoryByPurchaseId(@PathVariable(value = "purchaseId")long purchaseId,
            @PathVariable(value="userId")long userId, @PathVariable(value="orderId")long orderId){
        return new ResponseEntity<>(purchaseHistoryService.getPurChaseHistoryById(purchaseId,userId,orderId),HttpStatus.OK);
    }
//    @GetMapping("/{userId}/{orderId}")
//    public ResponseEntity<PurchaseHistoryDto> getPurchaseHistoryByOrderId( @PathVariable(value="userId")long userId, @PathVariable(value="orderId")long orderId){
//        return new ResponseEntity<>(purchaseHistoryService.getPurchaseHistoryByOrderId(userId,orderId),HttpStatus.OK);
//    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<PurchaseHistoryDto>> getPurchaseHistoryByUserId(@PathVariable(value="userId")long userId){
        return new ResponseEntity<>(purchaseHistoryService.getAllPurchaseHistoryByUserId(userId),HttpStatus.OK);
    }

    @PutMapping("/{purchaseId}/{userId}/{orderId}")
    public ResponseEntity<PurchaseHistoryDto> updatePurchaseHistory(@PathVariable(value = "purchaseId")long purchaseId,
     @PathVariable(value="userId")long userId, @PathVariable(value="orderId")long orderId,  @RequestBody PurchaseHistoryDto purchaseHistoryDto){
        return new ResponseEntity<>(purchaseHistoryService.updatePurchaseHistory(purchaseId,userId,orderId,purchaseHistoryDto),HttpStatus.OK);
    }
    @DeleteMapping("/{purchaseId}/{userId}/{orderId}")
    public ResponseEntity<String> deletePurchaseHistory(@PathVariable(value = "purchaseId")long purchaseId,
     @PathVariable(value="userId")long userId, @PathVariable(value="orderId")long orderId){
        purchaseHistoryService.deletePurchaseHistory(purchaseId,userId,orderId);
        return new ResponseEntity<>("PurchaseHistory deleted successfully",HttpStatus.OK);
    }
}
