package com.example.e_commerce_rest_api.utils.exception.handler;

import com.example.e_commerce_rest_api.enums.MessageKey;
import com.example.e_commerce_rest_api.dto.response.base.BaseResponse;
import com.example.e_commerce_rest_api.utils.exception.exceptions.*;
import com.example.e_commerce_rest_api.utils.helper.MessageSourceHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSourceHelper messageSourceHelper;

    public GlobalExceptionHandler(MessageSourceHelper messageSourceHelper) {
        this.messageSourceHelper = messageSourceHelper;
    }

    /*
        MethodArgumentNotValidException             400 Bad Request                 Validasyon hatalarında
        MissingServletRequestParameterException     400 Bad Request                 Request de eksik parametre olduğunda
        JpaSystemException                          400 Bad Request                 Veritabanı işlemlerinde hata olduğunda
        HttpMessageNotReadableException             400 Bad Request                 Http isteği sırasında JSON (body) verisi düzgün okunamadığında
        UnauthorizedException	                    401 Unauthorized	            Kimliği doğrulanmamış isteklerde
        ForbiddenException	                        403 Forbidden	                Kullanıcının yetkisi olmadığında
        NotFoundException	                        404 Not Found	                Kaynak bulunamadığında
        ConflictException	                        409 Conflict	                Aynı veriyi tekrar ekleme hatasında
        RateLimitException	                        429 Too Many Request	        İstek limiti aşıldığında
        BusinessLogicException                      422 Unprocessable Entity        İş mantığında bir hata oluştuysa
        Exception	                                500 Internal Server Error	    Genel sunucu hatalarında
    */

    /**
     * 400 - Bad Request - Validasyon hatalarında
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorsHashMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->errorsHashMap.put(error.getField(), error.getDefaultMessage()));
        List<String> errors = errorsHashMap.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.error()
                .addMessages(errors));
    }

    /**
     * 400 - Bad Request - Request de eksik parametre olduğunda
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<BaseResponse<Object>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String errorMessage = messageSourceHelper.getMessage(MessageKey.PLEASE_ADD_ALL_REQUIRED_PARAMETERS) + " (" + ex.getParameterName() + ")";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.error()
                .addMessage(errorMessage));
    }

    /**
     * 400 - Bad Request - Veritabanı işlemlerinde hata olduğunda
     */
    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse<Object>> handleJpaSystemException(JpaSystemException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.error()
                .addMessage(messageSourceHelper.getMessage(MessageKey.JPA_SYSTEM_EXCEPTION_MESSAGE))
                .addMessage(ex.getMessage()));
    }

    /**
     * 400 - Bad Request - Http isteği sırasında JSON (body) verisi düzgün okunamadığında
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.error()
                .addMessage(messageSourceHelper.getMessage(MessageKey.HTTP_MESSAGE_NOT_READABLE_EXCEPTION_MESSAGE)));
    }

    /**
     * 401 - Unauthorized - Kimliği doğrulanmamış isteklerde
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<BaseResponse<Object>> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.error()
                .addMessage(ex.getMessage()));
    }

    /**
     * 403 - Forbidden - Kullanıcının yetkisi olmadığında
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<BaseResponse<Object>> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BaseResponse.error()
                .addMessage(messageSourceHelper.getMessage(MessageKey.FORBIDDEN_MESSAGE)));
    }

    /**
     * 404 - Not Found - Kaynak bulunamadığında
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse<Object>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error()
                .addMessage(ex.getMessage()));
    }

    /**
     * 409 - Conflict - Aynı veriyi tekrar ekleme hatasında
     */
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BaseResponse<Object>> handleConflictException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponse.error()
                .addMessage(ex.getMessage()));
    }

    /**
     * 429 - Too Many Request - İstek limiti aşıldığında
     */
    @ExceptionHandler(RateLimitException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseEntity<BaseResponse<Object>> handleRateLimitException(RateLimitException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(BaseResponse.error()
                .addMessage(ex.getMessage()));
    }

    /**
     * 422 - Unprocessable Entity - İş mantığında bir hata oluştuysa
     */
    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<BaseResponse<Object>> handleBusinessLogicException(BusinessLogicException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(BaseResponse.error()
                .addMessage(ex.getMessage()));
    }

    /**
     * 500 - Internal Server Error - Genel sunucu hatalarında
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse<Object>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.error()
                .addMessage(messageSourceHelper.getMessage(MessageKey.INTERNAL_SERVER_ERROR_MESSAGE))
                .addMessage(ex.getMessage())
                .addMessages(Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toList()));
    }
}
