package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.model.request.InsertProductRequest;
import com.example.e_commerce_rest_api.model.request.UpdateProductRequest;
import com.example.e_commerce_rest_api.model.response.ProductDto;
import com.example.e_commerce_rest_api.model.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.impl.ProductService;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/product")
public class ProductController extends BaseController {
    private final ProductService productService;

    public ProductController(MessageSourceHelper messageSourceHelper, ProductService productService) {
        super(messageSourceHelper);
        this.productService = productService;
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping()
    public ResponseEntity<BaseResponse<List<ProductDto>>> findAll() {
        return ok(productService.findAll());
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping(path = "/by-category-id/{id}")
    public ResponseEntity<BaseResponse<List<ProductDto>>> findAllByCategoryName(@PathVariable Long id) {
        return ok(productService.findAllByCategoryId(id));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<ProductDto>> findById(@PathVariable Long id) {
        return ok(productService.findById(id));
    }

    @PreAuthorize(Authorize.ADMIN)
    @PostMapping()
    public ResponseEntity<BaseResponse<ProductDto>> save(@Valid @RequestBody InsertProductRequest request) {
        return ok(productService.save(request));
    }

    @PreAuthorize(Authorize.ADMIN)
    @PutMapping()
    public ResponseEntity<BaseResponse<ProductDto>> update(@Valid @RequestBody UpdateProductRequest request) {
        return ok(productService.update(request));
    }

    @PreAuthorize(Authorize.ADMIN)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ok(null);
    }
}