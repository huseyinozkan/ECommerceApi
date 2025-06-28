package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.model.request.InsertCategoryRequest;
import com.example.e_commerce_rest_api.model.request.UpdateCategoryRequest;
import com.example.e_commerce_rest_api.model.response.CategoryDto;
import com.example.e_commerce_rest_api.model.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.impl.CategoryService;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/category")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    public CategoryController(MessageSourceHelper messageSourceHelper, CategoryService categoryService) {
        super(messageSourceHelper);
        this.categoryService = categoryService;
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping()
    public ResponseEntity<BaseResponse<List<CategoryDto>>> findAll() {
        return ok(categoryService.findAll());
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<CategoryDto>> findById(@PathVariable Long id) {
        return ok(categoryService.findById(id));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PostMapping()
    public ResponseEntity<BaseResponse<CategoryDto>> save(@Valid @RequestBody InsertCategoryRequest request) {
        return ok(categoryService.save(request));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PutMapping()
    public ResponseEntity<BaseResponse<CategoryDto>> update(@Valid @RequestBody UpdateCategoryRequest request) {
        return ok(categoryService.update(request));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ok(null);
    }
}