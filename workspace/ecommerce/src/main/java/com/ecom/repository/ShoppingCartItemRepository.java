package com.ecom.repository;

import com.ecom.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Long> {
    List<ShoppingCartItem> findByCustomerUserId(long userId);
}
