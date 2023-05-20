package com.ecom.controller;

import com.ecom.payload.OrderDetailDto;
import com.ecom.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController {
    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    @PostMapping("/{userId}/{type}/{stateName}")
    public ResponseEntity<OrderDetailDto> createOrderDetail(@PathVariable(value="userId")long userId
    ,@PathVariable(value = "type")String type, @PathVariable(value = "stateName")String stateName,
    @RequestBody OrderDetailDto orderDetailDto){
        return new ResponseEntity<>(orderDetailService.createOrderDetail(orderDetailDto,userId,type,stateName), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/{orderId}")
    public ResponseEntity<OrderDetailDto> getOrderDetailByOrderId(@PathVariable(value="userId")long userId
    ,@PathVariable(value="orderId")long orderId){
        return new ResponseEntity<>(orderDetailService.getOrderDetail(userId, orderId),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDetailDto>> getAllOrderByUserId(@PathVariable(value="userId")long userId){
        return new ResponseEntity<>(orderDetailService.getAllOrderDetail(userId),HttpStatus.OK);
    }
    @PutMapping("/{userId}/{orderId}/{type}/{stateName}")
    public ResponseEntity<OrderDetailDto> updateOrderDetail(@PathVariable(value="userId")long userId,
            @PathVariable(value="orderId")long orderId,@PathVariable(value = "type")String type,
            @PathVariable(value = "stateName")String stateName, @RequestBody OrderDetailDto orderDetailDto){
        return new ResponseEntity<>(orderDetailService.updateOrderDetail(userId,orderId,type,stateName,orderDetailDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable(value="userId")long userId
            ,@PathVariable(value="orderId")long orderId){
        orderDetailService.deleteOrderDetail(userId,orderId);
        return new ResponseEntity<>("Order deleted successfully",HttpStatus.OK);
    }
}
