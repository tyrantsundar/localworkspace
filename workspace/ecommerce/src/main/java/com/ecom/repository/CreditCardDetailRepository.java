package com.ecom.repository;

import com.ecom.entities.CreditCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardDetailRepository extends JpaRepository<CreditCardDetail,String> {
    List<CreditCardDetail> findByCustomerUserId(long userId);
}
