package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.entity.Agreement;
import com.example.e_commerce_rest_api.enums.AgreementType;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.model.request.InsertAgreementRequest;
import com.example.e_commerce_rest_api.model.request.UpdateAgreementRequest;
import com.example.e_commerce_rest_api.model.response.AgreementDto;
import com.example.e_commerce_rest_api.repository.AgreementRepository;
import com.example.e_commerce_rest_api.service.IAgreementService;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AgreementService implements IAgreementService {

    private final ModelMapper modelMapper;
    private final MessageSourceHelper messageSourceHelper;
    private final AgreementRepository agreementRepository;
    private final UserService userService;

    @Override
    public List<AgreementDto> findAll() {
        return agreementRepository.findAll()
                .stream()
                .map(agreement -> modelMapper.map(agreement, AgreementDto.class))
                .toList();
    }

    @Override
    public AgreementDto findById(Long id) {
        return modelMapper.map(
                findAgreementById(id),
                AgreementDto.class
        );
    }

    @Override
    public List<AgreementDto> currentAgreements() {
        return  Stream.of(AgreementType.PRIVACY_AGREEMENT, AgreementType.USER_AGREEMENT)
                .map(type -> findLatestAgreement(type, LocaleContextHolder.getLocale().toLanguageTag()).or(() -> findLatestAgreement(type, "en-EN")))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(agreement -> modelMapper.map(agreement, AgreementDto.class))
                .collect(Collectors.toList());
    }

    private Optional<Agreement> findLatestAgreement(AgreementType type, String cultureName) {
        return agreementRepository.findTopByTypeAndCultureNameOrderByVersionDesc(type, cultureName);
    }

    @Override
    public AgreementDto save(InsertAgreementRequest request) {
        return modelMapper.map(
                agreementRepository.save(modelMapper.map(request, Agreement.class)),
                AgreementDto.class
        );
    }

    @Override
    public AgreementDto update(UpdateAgreementRequest request) {
        Agreement agreement = findAgreementById(request.getId());
        modelMapper.map(request, agreement);
        return modelMapper.map(agreementRepository.save(agreement), AgreementDto.class);
    }

    @Override
    public void deleteById(Long id) {
        findAgreementById(id);
        agreementRepository.deleteById(id);
    }

    private Agreement findAgreementById(Long id) {
        return agreementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.NOT_FOUND_WITH_ID, new Object[]{"Agreement", id.toString()})));
    }
}
