package com.example.e_commerce_rest_api.enums;

import org.springframework.http.MediaType;

public enum FileType {
    JPEG,
    PNG,
    GIF,
    PDF,
    DOCX,
    XLSX,
    OTHER;

    public MediaType toMediaType() {
        try {
            switch (this) {
                case JPEG:
                    return MediaType.IMAGE_JPEG;
                case PNG:
                    return MediaType.IMAGE_PNG;
                case GIF:
                    return MediaType.IMAGE_GIF;
                case PDF:
                    return MediaType.APPLICATION_PDF;
                default:
                    return MediaType.APPLICATION_JSON;
            }
        } catch (IllegalArgumentException e) {
            return MediaType.APPLICATION_JSON;
        }
    }
}