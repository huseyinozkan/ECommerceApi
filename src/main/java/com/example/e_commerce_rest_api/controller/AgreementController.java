package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.dto.request.InsertAgreementRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateAgreementRequest;
import com.example.e_commerce_rest_api.dto.response.AgreementDto;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.AgreementService;
import com.example.e_commerce_rest_api.service.impl.AgreementServiceImpl;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/agreement")
public class AgreementController extends BaseController {
    private final AgreementService agreementService;

    public AgreementController(MessageSourceHelper messageSourceHelper, AgreementService agreementService) {
        super(messageSourceHelper);
        this.agreementService = agreementService;
    }

    @PreAuthorize(Authorize.ADMIN)
    @GetMapping()
    public ResponseEntity<BaseResponse<List<AgreementDto>>> findAll() {
        return ok(agreementService.findAll());
    }

    @PreAuthorize(Authorize.ADMIN)
    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<AgreementDto>> findById(@PathVariable Long id) {
        return ok(agreementService.findById(id));
    }

    @GetMapping(path = "/current")
    public ResponseEntity<BaseResponse<List<AgreementDto>>> currentAgreements() {
        return ok(agreementService.currentAgreements());
    }

    @PreAuthorize(Authorize.ADMIN)
    @PostMapping()
    public ResponseEntity<BaseResponse<AgreementDto>> save(@Valid @RequestBody InsertAgreementRequest request) {
        return ok(agreementService.save(request));
    }

    @PreAuthorize(Authorize.ADMIN)
    @PutMapping()
    public ResponseEntity<BaseResponse<AgreementDto>> update(@Valid @RequestBody UpdateAgreementRequest request) {
        return ok(agreementService.update(request));
    }

    @PreAuthorize(Authorize.ADMIN)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable Long id) {
        agreementService.deleteById(id);
        return ok(null);
    }
}