package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.model.request.InsertCartItemRequest;
import com.example.e_commerce_rest_api.model.response.CartItemDto;
import com.example.e_commerce_rest_api.model.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.impl.CartItemService;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/cart-item")
public class CartItemController extends BaseController {
    private final CartItemService cartItemService;

    public CartItemController(MessageSourceHelper messageSourceHelper, CartItemService cartItemService) {
        super(messageSourceHelper);
        this.cartItemService = cartItemService;
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping()
    public ResponseEntity<BaseResponse<List<CartItemDto>>> findAll() {
        return ok(cartItemService.findAll());
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PostMapping()
    public ResponseEntity<BaseResponse<CartItemDto>> save(@Valid @RequestBody InsertCartItemRequest request) {
        return ok(cartItemService.save(request));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable Long id) {
        cartItemService.deleteById(id);
        return ok(null);
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @DeleteMapping(path = "/delete-all")
    public ResponseEntity<BaseResponse<Void>> deleteAll() {
        cartItemService.deleteAll();
        return ok(null);
    }
}