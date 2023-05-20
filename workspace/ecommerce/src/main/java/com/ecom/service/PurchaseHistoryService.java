package com.ecom.service;

import com.ecom.payload.PurchaseHistoryDto;

import java.util.List;

public interface PurchaseHistoryService {
    PurchaseHistoryDto createPurchaseHistory(PurchaseHistoryDto purchaseHistoryDto, long userId, long orderId);
    PurchaseHistoryDto getPurChaseHistoryById(long purchaseId, long userId, long orderId);
    PurchaseHistoryDto getPurchaseHistoryByOrderId(long userId,long orderId);
    List<PurchaseHistoryDto> getAllPurchaseHistoryByUserId(long userId);
    PurchaseHistoryDto updatePurchaseHistory(long purchaseId, long userId, long orderId, PurchaseHistoryDto purchaseHistoryDto);
    void deletePurchaseHistory(long purchaseId, long userId, long orderId);
}
