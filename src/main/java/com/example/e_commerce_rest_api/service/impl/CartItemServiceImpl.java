package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.CartItem;
import com.example.e_commerce_rest_api.entity.Product;
import com.example.e_commerce_rest_api.entity.User;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.dto.request.InsertCartItemRequest;
import com.example.e_commerce_rest_api.dto.response.CartItemDto;
import com.example.e_commerce_rest_api.repository.CartItemRepository;
import com.example.e_commerce_rest_api.repository.ProductRepository;
import com.example.e_commerce_rest_api.service.CartItemService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final UserServiceImpl userService;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final AddressServiceImpl addressService;

    @Override
    public List<CartItemDto> findAll() {
        User user = userService.getUser();
        return cartItemRepository.findAllByUserIdAndOrderNull(user.getId())
                .stream()
                .map(cartItem -> modelMapper.map(cartItem, CartItemDto.class))
                .toList();
    }

    @Override
    public CartItemDto save(InsertCartItemRequest request) {
        User user = userService.getUser();
        Product product = getProductById(request.getProductId());

        CartItem cartItem = CartItem.builder()
                .user(user)
                .product(product)
                .build();

        return modelMapper.map(cartItemRepository.save(cartItem), CartItemDto.class);
    }

    @Override
    public void deleteById(Long id) {
        CartItem cartItem = getCartItemById(id);
        cartItemRepository.delete(cartItem);
    }

    @Override
    @Transactional
    public void deleteAll() {
        User user = userService.getUser();
        cartItemRepository.deleteAllByUserId(user.getId());
    }

    private CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.CART_ITEM_NOT_FOUND)));
    }

    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.PRODUCT_NOT_FOUND)));
    }
}