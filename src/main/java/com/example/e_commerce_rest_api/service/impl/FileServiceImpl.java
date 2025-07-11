package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.File;
import com.example.e_commerce_rest_api.enums.FileType;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.dto.response.FileDto;
import com.example.e_commerce_rest_api.repository.FileRepository;
import com.example.e_commerce_rest_api.service.FileService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.BusinessLogicException;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final UserServiceImpl userService;
    private final FileRepository fileRepository;

    @Override
    public FileDto findById(Long id) {
        File file = getFileById(id);
        return modelMapper.map(file, FileDto.class);
    }

    @Override
    public ResponseEntity<byte[]> findByIdByte(Long id) {
        File file = getFileById(id);
        byte[] fileData = file.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(file.getFileType().toMediaType());
        headers.setContentLength(fileData.length);

        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }

    @Override
    public FileDto save(FileType fileType, MultipartFile multipartFile) {
        File file = File.builder()
                .fileType(fileType)
                .data(getFileBytes(multipartFile))
                .build();
        file.setFileName(UUID.randomUUID().toString());
        return modelMapper.map(fileRepository.save(file), FileDto.class);
    }

    @Override
    public FileDto update(Long id, FileType fileType, MultipartFile multipartFile) {
        File file = getFileById(id);
        file.setFileType(fileType);
        file.setData(getFileBytes(multipartFile));
        return modelMapper.map(fileRepository.save(file), FileDto.class);
    }

    @Override
    public void deleteById(Long id) {
        File file = getFileById(id);
        fileRepository.delete(file);
    }

    public File getFileById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.FILE_NOT_FOUND)));
    }

    private byte[] getFileBytes(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (Exception e) {
            throw new BusinessLogicException(messageSourceHelper.getMessage(MessageKey.FILE_SAVE_ERROR));
        }
    }
}