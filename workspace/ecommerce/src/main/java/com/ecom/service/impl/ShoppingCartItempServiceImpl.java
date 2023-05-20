package com.ecom.service.impl;

import com.ecom.entities.Book;
import com.ecom.entities.Customer;
import com.ecom.entities.ShoppingCartItem;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.ShoppingCartItemDto;
import com.ecom.repository.BookRepository;
import com.ecom.repository.CustomerRepository;
import com.ecom.repository.ShoppingCartItemRepository;
import com.ecom.service.ShoppingCartItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ShoppingCartItempServiceImpl implements ShoppingCartItemService {
    private CustomerRepository customerRepository;
    private BookRepository bookRepository;
    private ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartItempServiceImpl(CustomerRepository customerRepository, BookRepository bookRepository, ShoppingCartItemRepository shoppingCartItemRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }
    private ShoppingCartItemDto mapToDto(ShoppingCartItem shoppingCartItem){
        ShoppingCartItemDto shoppingCartItemDto = new ShoppingCartItemDto();
        shoppingCartItemDto.setShoppingCartId(shoppingCartItem.getShoppingCartId());
        shoppingCartItemDto.setDate(shoppingCartItem.getDate());
        shoppingCartItemDto.setPrice(shoppingCartItem.getPrice());
        shoppingCartItemDto.setQuantity(shoppingCartItem.getQuantity());
        return shoppingCartItemDto;
    }
    private ShoppingCartItem mapToEntity(ShoppingCartItemDto shoppingCartItemDto){
        ShoppingCartItem shoppingCartItemEntity = new ShoppingCartItem();
        shoppingCartItemEntity.setShoppingCartId(shoppingCartItemDto.getShoppingCartId());
        shoppingCartItemEntity.setDate(shoppingCartItemDto.getDate());
        shoppingCartItemEntity.setPrice(shoppingCartItemDto.getPrice());
        shoppingCartItemEntity.setQuantity(shoppingCartItemDto.getQuantity());
        return shoppingCartItemEntity;
    }
    private ShoppingCartItem updateEntity(ShoppingCartItemDto shoppingCartItemDto, ShoppingCartItem shoppingCartItemEntity){
//        shoppingCartItemEntity.setShoppingCartId(shoppingCartItemDto.getShoppingCartId());
        shoppingCartItemEntity.setDate(shoppingCartItemDto.getDate());
        shoppingCartItemEntity.setPrice(shoppingCartItemDto.getPrice());
        shoppingCartItemEntity.setQuantity(shoppingCartItemDto.getQuantity());
        return shoppingCartItemEntity;
    }
    @Override
    public ShoppingCartItemDto createShoppingCartItem(long userId, long bookId, ShoppingCartItemDto shoppingCartItemDto) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "BookId", bookId));
        ShoppingCartItem shoppingCartItem = mapToEntity(shoppingCartItemDto);
        shoppingCartItem.setCustomer(customer);
        shoppingCartItem.setBook(book);
        ShoppingCartItem savedShoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
        return mapToDto(savedShoppingCartItem);
    }

    @Override
    public ShoppingCartItemDto getShoppingCartItemById(long userId, long bookId, long shoppingCartId) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "BookId", bookId));
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartId).orElseThrow(()
                -> new ResourceNotFoundException("ShoppingCartItem", "ShoppingCartId", shoppingCartId));
        return mapToDto(shoppingCartItem);
    }

    @Override
    public List<ShoppingCartItemDto> getAllShoppingCartItemByCustomerId(long userId) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        return shoppingCartItemRepository.findByCustomerUserId(userId).stream().map(sci->mapToDto(sci)).collect(Collectors.toList());
    }

    @Override
    public ShoppingCartItemDto updateShoppingCartItem(long userId, long bookId, long shoppingCartId, ShoppingCartItemDto shoppingCartItemDto) {
        Customer customer = customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        Book book = bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "BookId", bookId));
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartId).orElseThrow(()
                -> new ResourceNotFoundException("ShoppingCartItem", "ShoppingCartId", shoppingCartId));
        shoppingCartItem = updateEntity(shoppingCartItemDto,shoppingCartItem);
        shoppingCartItem.setBook(book);
        shoppingCartItem.setCustomer(customer);
        ShoppingCartItem updatedShoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
        return mapToDto(updatedShoppingCartItem);
    }

    @Override
    public void deleteShoppingCartItem(long userId, long bookId, long shoppingCartId) {
        customerRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("Customer", "UserId", userId));
        bookRepository.findById(bookId).orElseThrow(()
                -> new ResourceNotFoundException("Book", "BookId", bookId));
        shoppingCartItemRepository.findById(shoppingCartId).orElseThrow(()
                -> new ResourceNotFoundException("ShoppingCartItem", "ShoppingCartId", shoppingCartId));
        shoppingCartItemRepository.deleteById(shoppingCartId);
    }
}
