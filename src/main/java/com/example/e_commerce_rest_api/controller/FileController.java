package com.example.e_commerce_rest_api.controller;

import com.example.e_commerce_rest_api.controller.base.BaseController;
import com.example.e_commerce_rest_api.enums.FileType;
import com.example.e_commerce_rest_api.dto.response.FileDto;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.service.FileService;
import com.example.e_commerce_rest_api.service.impl.FileServiceImpl;
import com.example.e_commerce_rest_api.utils.constants.Authorize;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/ecommerce/api/v1/file")
public class FileController extends BaseController {
    private final FileService fileService;

    public FileController(MessageSourceHelper messageSourceHelper, FileService fileService) {
        super(messageSourceHelper);
        this.fileService = fileService;
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<FileDto>> findById(@PathVariable Long id) {
        return ok(fileService.findById(id));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @GetMapping(path = "/byte/{id}")
    public ResponseEntity<byte[]> findByIdByte(@PathVariable Long id) {
        return fileService.findByIdByte(id);
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<FileDto>> save(@RequestParam FileType fileType,
                                                      @RequestParam("file") MultipartFile multipartFile) {
        return ok(fileService.save(fileType, multipartFile));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @PutMapping()
    public ResponseEntity<BaseResponse<FileDto>> update(@RequestParam Long id,
                                                        @RequestParam FileType fileType,
                                                        @RequestParam("file") MultipartFile multipartFile) {
        return ok(fileService.update(id, fileType, multipartFile));
    }

    @PreAuthorize(Authorize.USER_ADMIN)
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteById(@PathVariable Long id) {
        fileService.deleteById(id);
        return ok(null);
    }
}