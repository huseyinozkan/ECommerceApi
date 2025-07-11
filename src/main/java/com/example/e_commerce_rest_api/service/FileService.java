package com.example.e_commerce_rest_api.service;

import com.example.e_commerce_rest_api.enums.FileType;
import com.example.e_commerce_rest_api.dto.response.FileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileDto findById(Long id);
    ResponseEntity<byte[]> findByIdByte(Long id);
    FileDto save(FileType fileType, MultipartFile multipartFile);
    FileDto update (Long id, FileType fileType, MultipartFile multipartFile);
    void deleteById(Long id);
}