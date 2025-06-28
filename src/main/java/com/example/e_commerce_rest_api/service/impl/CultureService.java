package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.Culture;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.model.request.InsertCultureRequest;
import com.example.e_commerce_rest_api.model.request.UpdateCultureRequest;
import com.example.e_commerce_rest_api.model.response.CultureDto;
import com.example.e_commerce_rest_api.repository.CultureRepository;
import com.example.e_commerce_rest_api.service.ICultureService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CultureService implements ICultureService {

    private final ModelMapper modelMapper;
    private final CultureRepository cultureRepository;
    private final MessageSourceHelper messageSourceHelper;
    private final UserService userService;

    @Override
    public List<CultureDto> findAll() {
        return cultureRepository.findAll()
                .stream()
                .map(culture -> modelMapper.map(culture, CultureDto.class))
                .toList();
    }

    @Override
    public CultureDto findById(Long id) {
        return modelMapper.map(
                findCultureById(id),
                CultureDto.class
        );
    }

    @Override
    public CultureDto save(InsertCultureRequest request) {
        cultureExistsByName(request.getName());

        return modelMapper.map(
                cultureRepository.save(modelMapper.map(request, Culture.class)),
                CultureDto.class
        );
    }

    @Override
    public CultureDto update(UpdateCultureRequest request) {
        Culture culture = findCultureById(request.getId());
        modelMapper.map(request, culture);
        return modelMapper.map(cultureRepository.save(culture), CultureDto.class);
    }

    @Override
    public void deleteById(Long id) {
        findCultureById(id);
        cultureRepository.deleteById(id);
    }

    private Culture findCultureById(Long id) {
        return cultureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.NOT_FOUND_WITH_ID, new Object[]{"Culture", id})));
    }

    private void cultureExistsByName(String name) {
        if (cultureRepository.existsByName(name)) {
            throw new NotFoundException(messageSourceHelper.getMessage(MessageKey.CULTURE_ALREADY_EXISTS));
        }
    }
}
