package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.dto.request.InsertCultureRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateCultureRequest;
import com.example.e_commerce_rest_api.dto.response.CultureDto;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.CultureService;
import com.example.e_commerce_rest_api.service.impl.CultureServiceImpl;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/culture")
public class CultureController extends BaseController {
    private final CultureService cultureService;

    public CultureController(MessageSourceHelper messageSourceHelper, CultureService cultureService) {
        super(messageSourceHelper);
        this.cultureService = cultureService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<CultureDto>>> findAll() {
        return ok(cultureService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<CultureDto>> findById(@PathVariable Long id) {
        return ok(cultureService.findById(id));
    }

    @PreAuthorize(Authorize.ADMIN)
    @PostMapping()
    public ResponseEntity<BaseResponse<CultureDto>> save(@Valid @RequestBody InsertCultureRequest request) {
        return ok(cultureService.save(request));
    }

    @PreAuthorize(Authorize.ADMIN)
    @PutMapping()
    public ResponseEntity<BaseResponse<CultureDto>> update(@Valid @RequestBody UpdateCultureRequest request) {
        return ok(cultureService.update(request));
    }

    @PreAuthorize(Authorize.ADMIN)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable Long id) {
        cultureService.deleteById(id);
        return ok(null);
    }
}