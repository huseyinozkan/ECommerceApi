package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.Category;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.dto.request.InsertCategoryRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateCategoryRequest;
import com.example.e_commerce_rest_api.dto.response.CategoryDto;
import com.example.e_commerce_rest_api.repository.CategoryRepository;
import com.example.e_commerce_rest_api.service.CategoryService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = getCategoryById(id);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto save(InsertCategoryRequest request) {
        categoryRepository.findByName(request.getName())
                .ifPresent(existing -> {
                    throw new NotFoundException(messageSourceHelper.getMessage(MessageKey.CATEGORY_ALREADY_EXISTS));
                });

        Category category = modelMapper.map(request, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto update(UpdateCategoryRequest request) {
        categoryRepository.findByNameAndIdNot(request.getName(), request.getId())
                .ifPresent(existing -> {
                    throw new NotFoundException(messageSourceHelper.getMessage(MessageKey.CATEGORY_ALREADY_EXISTS));
                });

        Category category = getCategoryById(request.getId());
        modelMapper.map(request, category);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.CATEGORY_NOT_FOUND)));
    }
}