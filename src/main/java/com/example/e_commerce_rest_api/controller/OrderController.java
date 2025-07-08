package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.enums.OrderStatus;
import com.example.e_commerce_rest_api.model.request.*;
import com.example.e_commerce_rest_api.model.response.AddressDto;
import com.example.e_commerce_rest_api.model.response.OrderDto;
import com.example.e_commerce_rest_api.model.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.impl.OrderService;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/order")
public class OrderController extends BaseController {
    private final OrderService orderService;

    public OrderController(MessageSourceHelper messageSourceHelper, OrderService orderService) {
        super(messageSourceHelper);
        this.orderService = orderService;
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping()
    public ResponseEntity<BaseResponse<List<OrderDto>>> findAll() {
        return ok(orderService.findAll());
    }

    @PreAuthorize(Authorize.ADMIN)
    @GetMapping(path = "/admin")
    public ResponseEntity<BaseResponse<List<OrderDto>>> findAllAdmin() {
        return ok(orderService.findAllAdmin());
    }

    @PreAuthorize(Authorize.ADMIN)
    @GetMapping(path = "/admin/by-order-status")
    public ResponseEntity<BaseResponse<List<OrderDto>>> findAllAdminByOrderStatus(@RequestParam OrderStatus orderStatus) {
        return ok(orderService.findAllAdminByOrderStatus(orderStatus));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<OrderDto>> findById(@PathVariable Long id) {
        return ok(orderService.findById(id));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PostMapping()
    public ResponseEntity<BaseResponse<OrderDto>> save(@Valid @RequestBody InsertOrderRequest request) {
        return ok(orderService.save(request));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PutMapping(path = "/update-order-status")
    public ResponseEntity<BaseResponse<OrderDto>> updateOrderStatus(@Valid @RequestBody UpdateOrderStatusRequest request) {
        return ok(orderService.updateOrderStatus(request));
    }
}