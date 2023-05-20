package com.ecom.service.impl;

import com.ecom.entities.Customer;
import com.ecom.entities.OrderDetail;
import com.ecom.entities.PurchaseHistory;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.PurchaseHistoryDto;
import com.ecom.repository.CustomerRepository;
import com.ecom.repository.OrderDetailRepository;
import com.ecom.repository.PurchaseHistoryRepository;
import com.ecom.service.PurchaseHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {
    private PurchaseHistoryRepository purchaseHistoryRepository;
    private CustomerRepository customerRepository;
    private OrderDetailRepository orderDetailRepository;

    public PurchaseHistoryServiceImpl(PurchaseHistoryRepository purchaseHistoryRepository, CustomerRepository customerRepository, OrderDetailRepository orderDetailRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
        this.customerRepository = customerRepository;
        this.orderDetailRepository = orderDetailRepository;
    }
    private PurchaseHistoryDto mapToDto(PurchaseHistory purchaseHistory){
        PurchaseHistoryDto purchaseHistoryDto = new PurchaseHistoryDto();
        purchaseHistoryDto.setPurchaseId(purchaseHistory.getPurchaseId());
        purchaseHistoryDto.setDateOfPurchase(purchaseHistory.getDateOfPurchase());
        purchaseHistoryDto.setQuantity(purchaseHistory.getQuantity());
        purchaseHistoryDto.setPrice(purchaseHistory.getPrice());
        return purchaseHistoryDto;
    }
    private PurchaseHistory mapToEntity(PurchaseHistoryDto purchaseHistoryDto){
        PurchaseHistory purchaseHistoryEntity = new PurchaseHistory();
        purchaseHistoryEntity.setPurchaseId(purchaseHistoryDto.getPurchaseId());
        purchaseHistoryEntity.setDateOfPurchase(purchaseHistoryDto.getDateOfPurchase());
        purchaseHistoryEntity.setQuantity(purchaseHistoryDto.getQuantity());
        purchaseHistoryEntity.setPrice(purchaseHistoryDto.getPrice());
        return purchaseHistoryEntity;
    }
    private PurchaseHistory updateEntity(PurchaseHistoryDto purchaseHistoryDto, PurchaseHistory purchaseHistoryEntity){
        purchaseHistoryEntity.setDateOfPurchase(purchaseHistoryDto.getDateOfPurchase());
        purchaseHistoryEntity.setQuantity(purchaseHistoryDto.getQuantity());
        purchaseHistoryEntity.setPrice(purchaseHistoryDto.getPrice());
        return purchaseHistoryEntity;
    }
    @Override
    public PurchaseHistoryDto createPurchaseHistory(PurchaseHistoryDto purchaseHistoryDto, long userId, long orderId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        OrderDetail orderDetail = orderDetailRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        PurchaseHistory purchaseHistory = mapToEntity(purchaseHistoryDto);
        purchaseHistory.setCustomer(customer);
        purchaseHistory.setOrderDetail(orderDetail);
        PurchaseHistory savedPurchaseHistory = purchaseHistoryRepository.save(purchaseHistory);
        return mapToDto(savedPurchaseHistory);
    }

    @Override
    public PurchaseHistoryDto getPurChaseHistoryById(long purchaseId, long userId, long orderId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        PurchaseHistory purchaseHistory = purchaseHistoryRepository.findById(purchaseId).orElseThrow(()
                -> new ResourceNotFoundException("PurchaseHistory", "PurchaseId", purchaseId));
        return mapToDto(purchaseHistory);
    }

    @Override
    public PurchaseHistoryDto getPurchaseHistoryByOrderId(long userId,long orderId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        orderDetailRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        PurchaseHistory purchaseHistory = purchaseHistoryRepository.findByOrderDetailOrderId(orderId);
        return mapToDto(purchaseHistory);
    }

    @Override
    public List<PurchaseHistoryDto> getAllPurchaseHistoryByUserId(long userId) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        List<PurchaseHistory> customerPurchaseHistory = purchaseHistoryRepository.findByCustomerUserId(userId);
        return customerPurchaseHistory.stream().map(ph->mapToDto(ph)).collect(Collectors.toList());
    }

    @Override
    public PurchaseHistoryDto updatePurchaseHistory(long purchaseId, long userId, long orderId, PurchaseHistoryDto purchaseHistoryDto) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        PurchaseHistory purchaseHistory = purchaseHistoryRepository.findById(purchaseId).orElseThrow(()
                -> new ResourceNotFoundException("PurchaseHistory", "PurchaseId", purchaseId));
        purchaseHistory = updateEntity(purchaseHistoryDto,purchaseHistory);
        purchaseHistory.setCustomer(customer);
        purchaseHistory.setOrderDetail(orderDetail);
        PurchaseHistory updatedPurchaseHistory = purchaseHistoryRepository.save(purchaseHistory);
        return mapToDto(updatedPurchaseHistory);
    }

    @Override
    public void deletePurchaseHistory(long purchaseId, long userId, long orderId) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        orderDetailRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFoundException("OrderDetail", "OrderId", orderId));
        purchaseHistoryRepository.findById(purchaseId).orElseThrow(()
                -> new ResourceNotFoundException("PurchaseHistory", "PurchaseId", purchaseId));
        purchaseHistoryRepository.deleteById(purchaseId);
    }
}
