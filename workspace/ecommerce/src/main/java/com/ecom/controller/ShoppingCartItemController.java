package com.ecom.controller;

import com.ecom.payload.ShoppingCartItemDto;
import com.ecom.service.ShoppingCartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartItemController {
    private ShoppingCartItemService shoppingCartItemService;

    public ShoppingCartItemController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }
    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<ShoppingCartItemDto> createShoppingCartItem(@PathVariable (value = "userId")long userId
       , @PathVariable(value = "bookId")long bookId, @RequestBody ShoppingCartItemDto shoppingCartItemDto){
        return new ResponseEntity<>(shoppingCartItemService.createShoppingCartItem(userId,bookId,shoppingCartItemDto), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/{bookId}/{shoppingCartId}")
    public ResponseEntity<ShoppingCartItemDto> getShoppingCartItemById(@PathVariable (value = "userId")long userId
            , @PathVariable(value = "bookId")long bookId, @PathVariable(value = "shoppingCartId")long shoppingCartId){
        return new ResponseEntity<>(shoppingCartItemService.getShoppingCartItemById(userId,bookId,shoppingCartId), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<ShoppingCartItemDto>> getAllShoppingCartItemByCustomerId(@PathVariable (value = "userId")long userId){
        return new ResponseEntity<>(shoppingCartItemService.getAllShoppingCartItemByCustomerId(userId), HttpStatus.OK);
    }
    @PutMapping("/{userId}/{bookId}/{shoppingCartId}")
    public ResponseEntity<ShoppingCartItemDto> updateShoppingCartItem(@PathVariable (value = "userId")long userId
            , @PathVariable(value = "bookId")long bookId, @PathVariable(value = "shoppingCartId")long shoppingCartId, @RequestBody ShoppingCartItemDto shoppingCartItemDto){
        return new ResponseEntity<>(shoppingCartItemService.updateShoppingCartItem(userId,bookId,shoppingCartId,shoppingCartItemDto), HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/{bookId}/{shoppingCartId}")
    public ResponseEntity<String> deleteShippingType(@PathVariable (value = "userId")long userId
            , @PathVariable(value = "bookId")long bookId, @PathVariable(value = "shoppingCartId")long shoppingCartId){
        shoppingCartItemService.deleteShoppingCartItem(userId,bookId,shoppingCartId);
        return new ResponseEntity<>("ShoppingCart Deleted Successfully",HttpStatus.OK);
    }
}
