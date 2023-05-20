package com.ecom.repository;

import com.ecom.entities.ShippingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingTypeRepository extends JpaRepository<ShippingType,String> {
}
