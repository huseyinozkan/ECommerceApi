package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.model.request.UpsertResourceRequest;
import com.example.e_commerce_rest_api.model.response.LocalizationDto;
import com.example.e_commerce_rest_api.model.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.impl.ResourceService;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/resource")
public class ResourceController extends BaseController {
    private final ResourceService resourceService;

    public ResourceController(MessageSourceHelper messageSourceHelper, ResourceService resourceService) {
        super(messageSourceHelper);
        this.resourceService = resourceService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<BaseResponse<LocalizationDto>> findByName(@PathVariable String name) {
        return ok(resourceService.findByName(name));
    }

    @PreAuthorize(Authorize.ADMIN)
    @PostMapping("/upsert")
    public ResponseEntity<BaseResponse<Object>> upsert(@Valid @RequestBody UpsertResourceRequest request) {
        resourceService.upsert(request);
        return ok(null);
    }
}