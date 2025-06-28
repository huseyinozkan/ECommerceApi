package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.model.request.UpdatePaymentStatusRequest;
import com.example.e_commerce_rest_api.model.response.PaymentDto;
import com.example.e_commerce_rest_api.model.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.impl.PaymentService;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/payment")
public class PaymentController extends BaseController {
    private final PaymentService paymentService;

    public PaymentController(MessageSourceHelper messageSourceHelper, PaymentService paymentService) {
        super(messageSourceHelper);
        this.paymentService = paymentService;
    }

    @PreAuthorize(Authorize.ADMIN)
    @PutMapping(path = "update-payment-status")
    public ResponseEntity<BaseResponse<PaymentDto>> updatePaymentStatus(@Valid @RequestBody UpdatePaymentStatusRequest request) {
        return ok(paymentService.updatePaymentStatus(request));
    }
}