package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.Culture;
import com.example.e_commerce_rest_api.entity.Resource;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.model.request.UpsertResourceRequest;
import com.example.e_commerce_rest_api.model.response.CultureDto;
import com.example.e_commerce_rest_api.model.response.LocalizationDto;
import com.example.e_commerce_rest_api.model.response.ResourceDto;
import com.example.e_commerce_rest_api.repository.CultureRepository;
import com.example.e_commerce_rest_api.repository.ResourceRepository;
import com.example.e_commerce_rest_api.service.IResourceService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResourceService implements IResourceService {
    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final CultureRepository cultureRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public LocalizationDto findByName(String name) {
        Culture culture = cultureFindByName(name);
        CultureDto cultureDto = modelMapper.map(culture, CultureDto.class);

        List<Resource> resources = resourceRepository.findByCultureId(culture.getId());
        List<ResourceDto> resourceDtos = resources.stream()
                .map(resource -> modelMapper.map(resource, ResourceDto.class))
                .collect(Collectors.toList());

        return new LocalizationDto(cultureDto, resourceDtos);
    }

    @Override
    public void upsert(UpsertResourceRequest request) {
        Culture culture = cultureFindByName(request.getName());

        Map<String, Resource> existingResourcesMap = resourceRepository.findByCultureId(culture.getId())
                .stream()
                .collect(Collectors.toMap(Resource::getKey, resource -> resource));

        List<Resource> updatedResources = request.getResources().stream().map(resourceRequest -> {
            if (existingResourcesMap.containsKey(resourceRequest.getKey())) {
                Resource resource = existingResourcesMap.get(resourceRequest.getKey());
                resource.setValue(resourceRequest.getValue());
                return resource;
            } else {
                return new Resource(resourceRequest.getKey(), resourceRequest.getValue(), culture);
            }
        }).collect(Collectors.toList());

        resourceRepository.saveAll(updatedResources);
    }

    private Culture cultureFindByName(String name) {
        return cultureRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.CULTURE_NOT_FOUND)));
    }
}
