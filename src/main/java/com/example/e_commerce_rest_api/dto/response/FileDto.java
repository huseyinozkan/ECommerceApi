package com.example.e_commerce_rest_api.dto.response;

import com.example.e_commerce_rest_api.enums.FileType;
import com.example.e_commerce_rest_api.dto.response.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto extends BaseDto {
    private String fileName;
    private FileType fileType;
    private byte[] data;
}
