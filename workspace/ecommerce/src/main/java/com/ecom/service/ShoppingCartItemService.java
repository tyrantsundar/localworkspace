package com.ecom.service;

import com.ecom.payload.ShoppingCartItemDto;

import java.util.List;

public interface ShoppingCartItemService {
    ShoppingCartItemDto createShoppingCartItem(long userId, long bookId, ShoppingCartItemDto shoppingCartItemDto);
    ShoppingCartItemDto getShoppingCartItemById(long userId, long bookId, long shoppingCartId);
    List<ShoppingCartItemDto> getAllShoppingCartItemByCustomerId(long userId);
    ShoppingCartItemDto updateShoppingCartItem(long userId, long bookId, long shoppingCartId, ShoppingCartItemDto shoppingCartItemDto);
    void deleteShoppingCartItem(long userId, long bookId, long shoppingCartId);
}
