package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.*;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.model.request.InsertAddressRequest;
import com.example.e_commerce_rest_api.model.request.InsertProductRequest;
import com.example.e_commerce_rest_api.model.request.UpdateAddressRequest;
import com.example.e_commerce_rest_api.model.request.UpdateProductRequest;
import com.example.e_commerce_rest_api.model.response.ProductDto;
import com.example.e_commerce_rest_api.repository.ProductRepository;
import com.example.e_commerce_rest_api.service.IProductService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final CategoryService categoryService;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public List<ProductDto> findAllByCategoryId(Long id) {
        return productRepository.findAllByCategoryId(id)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }


    @Override
    public ProductDto findById(Long id) {
        Product product = getProductById(id);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto save(InsertProductRequest request) {
        File imagefile = fileService.getFileById(request.getImageFileId());
        Category category = categoryService.getCategoryById(request.getCategoryId());

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageFile(imagefile)
                .category(category)
                .build();

        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public ProductDto update(UpdateProductRequest request) {
        Product product = getProductById(request.getId());
        File imagefile = fileService.getFileById(request.getImageFileId());
        Category category = categoryService.getCategoryById(request.getCategoryId());

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageFile(imagefile);
        product.setCategory(category);

        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.PRODUCT_NOT_FOUND)));
    }
}