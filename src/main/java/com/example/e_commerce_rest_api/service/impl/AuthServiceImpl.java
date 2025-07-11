package com.example.e_commerce_rest_api.service.impl;

import com.example.e_commerce_rest_api.enums.OtpPurpose;
import com.example.e_commerce_rest_api.repository.UserRepository;
import com.example.e_commerce_rest_api.entity.*;
import com.example.e_commerce_rest_api.entity.base.BaseEntity;
import com.example.e_commerce_rest_api.enums.AgreementType;
import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.enums.RoleType;
import com.example.e_commerce_rest_api.dto.request.*;
import com.example.e_commerce_rest_api.dto.response.AgreementDto;
import com.example.e_commerce_rest_api.dto.response.LoginResponse;
import com.example.e_commerce_rest_api.dto.response.UserDto;
import com.example.e_commerce_rest_api.repository.*;
import com.example.e_commerce_rest_api.service.AuthService;
import com.example.e_commerce_rest_api.utils.config.properties.AppSecurityProperties;
import com.example.e_commerce_rest_api.utils.exception.exceptions.BusinessLogicException;
import com.example.e_commerce_rest_api.utils.exception.exceptions.NotFoundException;
import com.example.e_commerce_rest_api.utils.exception.exceptions.RateLimitException;
import com.example.e_commerce_rest_api.utils.exception.exceptions.UnauthorizedException;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import com.example.e_commerce_rest_api.utils.helper.OtpHelper;
import com.example.e_commerce_rest_api.utils.helper.RateLimitHelper;
import com.example.e_commerce_rest_api.utils.jwt.impl.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;
    private final MessageSourceHelper messageSourceHelper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AppSecurityProperties appSecurityProperties;
    private final RoleRepository roleRepository;
    private final RateLimitHelper rateLimitHelper;
    private final UserServiceImpl userService;
    private final AgreementRepository agreementRepository;
    private final AgreementServiceImpl agreementService;
    private final OtpVerificationRepository otpVerificationRepository;
    private final OtpHelper otpHelper;

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.USER_NOT_FOUND)));

            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));

            String accessToken = jwtUtils.generateToken(user);
            RefreshToken refreshToken = createRefreshToken(user);
            refreshTokenRepository.save(refreshToken);

            Boolean privacyAgreementHasBeenApproved = agreementHasBeenApproved(user.getPrivacyAgreement(), AgreementType.PRIVACY_AGREEMENT);
            Boolean userAgreementHasBeenApproved = agreementHasBeenApproved(user.getUserAgreement(), AgreementType.USER_AGREEMENT);

            return new LoginResponse(accessToken, refreshToken.getRefreshToken(), modelMapper.map(user, UserDto.class), privacyAgreementHasBeenApproved, userAgreementHasBeenApproved);
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException(messageSourceHelper.getMessage(MessageKey.INCORRECT_PASSWORD));
        }
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new UnauthorizedException(messageSourceHelper.getMessage(MessageKey.TOKEN_INVALID)));

        if (refreshToken.getExpireDate().before(new Date())) {
            throw new UnauthorizedException(messageSourceHelper.getMessage(MessageKey.TOKEN_EXPIRED));
        }

        String accessToken = jwtUtils.generateToken(refreshToken.getUser());
        RefreshToken newRefreshToken = createRefreshToken(refreshToken.getUser());
        refreshTokenRepository.save(newRefreshToken);

        Boolean privacyAgreementHasBeenApproved = agreementHasBeenApproved(refreshToken.getUser().getPrivacyAgreement(), AgreementType.PRIVACY_AGREEMENT);
        Boolean userAgreementHasBeenApproved = agreementHasBeenApproved(refreshToken.getUser().getUserAgreement(), AgreementType.USER_AGREEMENT);

        return new LoginResponse(accessToken, newRefreshToken.getRefreshToken(), modelMapper.map(refreshToken.getUser(), UserDto.class), privacyAgreementHasBeenApproved, userAgreementHasBeenApproved);
    }

    @Override
    @Transactional
    public UserDto register(RegisterRequest request) {
        userRepository.findByPhoneCodeAndPhoneNumber(request.getPhoneCode(), request.getPhoneNumber())
                .ifPresent(user -> {
                    throw new NotFoundException(messageSourceHelper.getMessage(MessageKey.PHONE_NUMBER_ALREADY_USED));
                });

        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new NotFoundException(messageSourceHelper.getMessage(MessageKey.USERNAME_ALREADY_USED));
                });

        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            userRepository.findByEmail(request.getEmail())
                    .ifPresent(user -> {
                        throw new NotFoundException(messageSourceHelper.getMessage(MessageKey.EMAIL_ALREADY_USED));
                    });
        }

        Role role = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.ROLE_NOT_FOUND)));

        List<AgreementDto> agreementDtos = agreementService.currentAgreements();
        AgreementDto privacyAgreement = agreementDtos.stream().filter(a -> a.getType() == AgreementType.PRIVACY_AGREEMENT).findFirst()
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.AGREEMENT_NOT_FOUND)));
        AgreementDto userAgreement = agreementDtos.stream().filter(a -> a.getType() == AgreementType.USER_AGREEMENT).findFirst()
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.AGREEMENT_NOT_FOUND)));

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneCode(request.getPhoneCode())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .privacyAgreement(modelMapper.map(privacyAgreement, Agreement.class))
                .userAgreement(modelMapper.map(userAgreement, Agreement.class))
                .build();

        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    @Transactional
    public void sendOtpCode(SendOtpCodeRequest request) {
        String requestKey = "send-otp-code-" + request.getPhoneCode() + request.getPhoneNumber();
        if (!rateLimitHelper.isAllowed(requestKey)) {
            throw new RateLimitException(messageSourceHelper.getMessage(MessageKey.RATE_LIMIT_EXCEPTION_MESSAGE));
        }

        User user = userRepository.findByPhoneCodeAndPhoneNumber(request.getPhoneCode(), request.getPhoneNumber())
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.USER_NOT_FOUND)));

        otpVerificationRepository.deleteAllByUserIdAndVerifiedAndPurpose(user.getId(), false, request.getPurpose());

        otpVerificationRepository.save(
                OtpVerification.builder()
                        .code(otpHelper.generateOtp())
                        .expiresAt(LocalDateTime.now().plusMinutes(3))
                        .verified(false)
                        .user(user)
                        .purpose(request.getPurpose())
                        .build()
        );
    }

    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByPhoneCodeAndPhoneNumber(request.getPhoneCode(), request.getPhoneNumber())
                .orElseThrow(() -> new NotFoundException(messageSourceHelper.getMessage(MessageKey.USER_NOT_FOUND)));

        OtpVerification otpVerification = otpVerificationRepository.findByUserIdAndCodeAndVerifiedAndPurpose(user.getId(), request.getCode(), false, OtpPurpose.FORGOT_PASSWORD)
                .orElseThrow(() -> new BusinessLogicException(messageSourceHelper.getMessage(MessageKey.INCORRECT_SECURITY_CODE)));

        if (otpVerification.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BusinessLogicException(messageSourceHelper.getMessage(MessageKey.SECURITY_CODE_EXPIRED));
        }

        otpVerification.setVerified(true);
        otpVerificationRepository.save(otpVerification);
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordRequest request) {
        User user = userService.getUser();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BusinessLogicException(messageSourceHelper.getMessage(MessageKey.INCORRECT_OLD_PASSWORD));
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void approveAgreements(ApproveAgreementRequest request) {
        User user = userService.getUser();
        for (AgreementIdAndType agreement : request.getAgreements()) {
            if (agreement.getType() == AgreementType.PRIVACY_AGREEMENT) updatePrivacyAgreement(user.getId(), agreement.getId());
            if (agreement.getType() == AgreementType.USER_AGREEMENT) updateUserAgreement(user.getId(), agreement.getId());
        }
    }

    public void updateUserAgreement(Long userId, Long agreementId) {
        userRepository.updateUserAgreementById(userId, agreementId);
    }

    public void updatePrivacyAgreement(Long userId, Long agreementId) {
        userRepository.updatePrivacyAgreementById(userId, agreementId);
    }


    private RefreshToken createRefreshToken(User user) {
        return RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .expireDate(new Date(System.currentTimeMillis() + 1000 * 60 * appSecurityProperties.getRefreshTokenExpirationMinute()))
                .user(user)
                .build();
    }

    private boolean agreementHasBeenApproved(Agreement agreement, AgreementType agreementType) {
        Optional<Agreement> lastAgreement = agreementRepository
                .findTopByTypeAndCultureNameOrderByVersionDesc(agreementType, LocaleContextHolder.getLocale().toLanguageTag())
                .or(() -> agreementRepository.findTopByTypeAndCultureNameOrderByVersionDesc(agreementType, "en-EN"));

        if (agreement == null && lastAgreement.isEmpty()) return true;
        if (agreement == null) return false;

        Long lastAgreementId = lastAgreement.map(BaseEntity::getId).orElse(null);
        return Objects.equals(agreement.getId(), lastAgreementId);
    }
}
