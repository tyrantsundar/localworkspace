package com.ecom.controller;

import com.ecom.payload.ShippingTypeDto;
import com.ecom.service.ShippingTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/shippingType")
public class ShippingTypeController {
    private ShippingTypeService shippingTypeService;

    public ShippingTypeController(ShippingTypeService shippingTypeService) {
        this.shippingTypeService = shippingTypeService;
    }
    @PostMapping
    public ResponseEntity<ShippingTypeDto> createShippingType(@RequestBody ShippingTypeDto shippingTypeDto){
        return new ResponseEntity<>(shippingTypeService.createShippingType(shippingTypeDto), HttpStatus.CREATED);
    }
    @GetMapping("/{type}")
    public ResponseEntity<ShippingTypeDto> getShippingTypeById(@PathVariable(value = "type")String type){
        return new ResponseEntity<>(shippingTypeService.getShippingTypeById(type),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ShippingTypeDto>> getAllShippingType(){
        return new ResponseEntity<>(shippingTypeService.getAllShippingType(),HttpStatus.OK);
    }
    @PutMapping("/{type}")
    public ResponseEntity<ShippingTypeDto> updateShippingType(@RequestBody ShippingTypeDto shippingTypeDto, @PathVariable(value = "type")String type){
        return new ResponseEntity<>(shippingTypeService.updateShippingType(type,shippingTypeDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{type}")
    public ResponseEntity<String> deleteShippingType(@PathVariable(value = "type")String type){
        shippingTypeService.deleteShippingType(type);
        return new ResponseEntity<>("ShippingType Deleted Successfully",HttpStatus.OK);
    }
}
