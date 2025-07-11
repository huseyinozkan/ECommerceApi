package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.Address;
import com.example.e_commerce_rest_api.entity.User;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.dto.request.InsertAddressRequest;
import com.example.e_commerce_rest_api.dto.request.UpdateAddressRequest;
import com.example.e_commerce_rest_api.dto.response.AddressDto;
import com.example.e_commerce_rest_api.repository.AddressRepository;
import com.example.e_commerce_rest_api.service.AddressService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final UserServiceImpl userService;
    private final AddressRepository addressRepository;

    @Override
    public List<AddressDto> findAll() {
        User user = userService.getUser();
        return addressRepository.findAllByUserId(user.getId())
                .stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .toList();
    }

    @Override
    public AddressDto findById(Long id) {
        Address address = getAddressById(id);
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public AddressDto save(InsertAddressRequest request) {
        User user = userService.getUser();
        Address address = modelMapper.map(request, Address.class);
        address.setUser(user);
        return modelMapper.map(addressRepository.save(address), AddressDto.class);
    }

    @Override
    public AddressDto update(UpdateAddressRequest request) {
        Address address = getAddressById(request.getId());
        modelMapper.map(request, address);
        return modelMapper.map(addressRepository.save(address), AddressDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Address address = getAddressById(id);
        addressRepository.delete(address);
    }

    public Address getAddressById(Long id) {
        User user = userService.getUser();
        return addressRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.ADDRESS_NOT_FOUND)));
    }
}