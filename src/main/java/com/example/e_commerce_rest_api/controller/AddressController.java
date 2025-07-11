package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.dto.request.InsertAddressRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateAddressRequest;
import com.example.e_commerce_rest_api.dto.response.AddressDto;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.AddressService;
import com.example.e_commerce_rest_api.service.impl.AddressServiceImpl;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/address")
public class AddressController extends BaseController {
    private final AddressService addressService;

    public AddressController(MessageSourceHelper messageSourceHelper, AddressService addressService) {
        super(messageSourceHelper);
        this.addressService = addressService;
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping()
    public ResponseEntity<BaseResponse<List<AddressDto>>> findAll() {
        return ok(addressService.findAll());
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<AddressDto>> findById(@PathVariable Long id) {
        return ok(addressService.findById(id));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PostMapping()
    public ResponseEntity<BaseResponse<AddressDto>> save(@Valid @RequestBody InsertAddressRequest request) {
        return ok(addressService.save(request));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PutMapping()
    public ResponseEntity<BaseResponse<AddressDto>> update(@Valid @RequestBody UpdateAddressRequest request) {
        return ok(addressService.update(request));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ok(null);
    }
}