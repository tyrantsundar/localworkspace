package com.ecom.service;

import com.ecom.entities.OrderDetail;
import com.ecom.payload.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto,long userId, String type, String stateName);
    OrderDetailDto getOrderDetail(long userId, long orderId);
    List<OrderDetailDto> getAllOrderDetail(long userId);
    OrderDetailDto updateOrderDetail(long userId, long orderId, String type, String stateName, OrderDetailDto orderDetailDto);
    void deleteOrderDetail(long userId,long orderId);
}
