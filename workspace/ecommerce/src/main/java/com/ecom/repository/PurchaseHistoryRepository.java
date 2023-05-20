package com.ecom.repository;

import com.ecom.entities.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory,Long> {
    PurchaseHistory findByOrderDetailOrderId(long orderId);
    List<PurchaseHistory> findByCustomerUserId(long userId);
}
