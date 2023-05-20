package com.ecom.service.impl;

import com.ecom.entities.Customer;
import com.ecom.entities.OrderDetail;
import com.ecom.entities.ShippingType;
import com.ecom.entities.StateTax;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.OrderDetailDto;
import com.ecom.repository.CustomerRepository;
import com.ecom.repository.OrderDetailRepository;
import com.ecom.repository.ShippingTypeRepository;
import com.ecom.repository.StateTaxRepository;
import com.ecom.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private CustomerRepository customerRepository;
    private OrderDetailRepository orderDetailRepository;
    private ShippingTypeRepository shippingTypeRepository;
    private StateTaxRepository stateTaxRepository;

    public OrderDetailServiceImpl(CustomerRepository customerRepository, OrderDetailRepository orderDetailRepository, ShippingTypeRepository shippingTypeRepository, StateTaxRepository stateTaxRepository) {
        this.customerRepository = customerRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.shippingTypeRepository = shippingTypeRepository;
        this.stateTaxRepository = stateTaxRepository;
    }
    private OrderDetailDto mapToDto(OrderDetail orderDetail){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setAddress(orderDetail.getAddress());
        orderDetailDto.setOrderId(orderDetail.getOrderId());
        orderDetailDto.setCity(orderDetail.getCity());
        orderDetailDto.setReceiverName(orderDetail.getReceiverName());
        orderDetailDto.setDateOfPurchase(orderDetail.getDateOfPurchase());
        return orderDetailDto;
    }
    private OrderDetail mapToEntity(OrderDetailDto orderDetailDto){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setAddress(orderDetailDto.getAddress());
        orderDetail.setOrderId(orderDetailDto.getOrderId());
        orderDetail.setCity(orderDetailDto.getCity());
        orderDetail.setReceiverName(orderDetailDto.getReceiverName());
        orderDetail.setDateOfPurchase(orderDetailDto.getDateOfPurchase());
        return orderDetail;
    }
    private OrderDetail updateEntity(OrderDetailDto orderDetailDto, OrderDetail orderDetail){
        orderDetail.setAddress(orderDetailDto.getAddress());
        orderDetail.setCity(orderDetailDto.getCity());
        orderDetail.setReceiverName(orderDetailDto.getReceiverName());
        orderDetail.setDateOfPurchase(orderDetailDto.getDateOfPurchase());
        return orderDetail;
    }

    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto, long userId,  String type, String stateName) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        StateTax stateTax = stateTaxRepository.findById(stateName).orElseThrow(()
                -> new ResourceNotFoundException("State", "stateName", stateName));
        ShippingType shippingType = shippingTypeRepository.findById(type).orElseThrow(()
                -> new ResourceNotFoundException("ShippingType", "type", type));
        OrderDetail orderDetail = mapToEntity(orderDetailDto);
        orderDetail.setCustomer(customer);
        orderDetail.setState(stateTax);
        orderDetail.setShippingType(shippingType);
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return mapToDto(savedOrderDetail);
    }

    @Override
    public OrderDetailDto getOrderDetail(long userId, long orderId) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        return mapToDto(orderDetail);
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetail(long userId) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        return orderDetailRepository.findByCustomerUserId(userId).stream().map(od->mapToDto(od)).collect(Collectors.toList());
    }

    @Override
    public OrderDetailDto updateOrderDetail(long userId, long orderId, String type, String stateName, OrderDetailDto orderDetailDto) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        StateTax stateTax = stateTaxRepository.findById(stateName).orElseThrow(()
                -> new ResourceNotFoundException("State", "stateName", stateName));
        ShippingType shippingType = shippingTypeRepository.findById(type).orElseThrow(()
                -> new ResourceNotFoundException("ShippingType", "type", type));
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        orderDetail = updateEntity(orderDetailDto,orderDetail);
        orderDetail.setShippingType(shippingType);
        orderDetail.setState(stateTax);
        orderDetail.setCustomer(customer);
        OrderDetail updatedOrderDetail = orderDetailRepository.save(orderDetail);
        return mapToDto(updatedOrderDetail);
    }

    @Override
    public void deleteOrderDetail(long userId, long orderId) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        orderDetailRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        orderDetailRepository.deleteById(orderId);
    }
}
