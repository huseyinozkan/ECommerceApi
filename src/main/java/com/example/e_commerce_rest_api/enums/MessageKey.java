package com.example.e_commerce_rest_api.enums;

import lombok.Getter;

@Getter
public enum MessageKey {
    OPERATION_SUCCESSFUL("operation.successful"),
    INTERNAL_SERVER_ERROR_MESSAGE("internal.server.error.message"),
    NOT_FOUND_WITH_ID("not.found.with.id"),
    USER_NOT_FOUND("user.not.found"),
    JPA_SYSTEM_EXCEPTION_MESSAGE("jpa.system.exception.message"),
    USERNAME_ALREADY_USED("username.already.used"),
    INCORRECT_PASSWORD("incorrect.password"),
    TOKEN_INVALID("token.invalid"),
    TOKEN_EXPIRED("token.expired"),
    UNAUTHORIZED_MESSAGE("unauthorized.message"),
    FORBIDDEN_MESSAGE("forbidden.message"),
    ROLE_NOT_FOUND("role.not.found"),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION_MESSAGE("http.message.not.readable.exception.message"),
    RATE_LIMIT_EXCEPTION_MESSAGE("rate.limit.exception.message"),
    INCORRECT_SECURITY_CODE("incorrect.security.code"),
    PHONE_NUMBER_ALREADY_USED("phone.number.already.used"),
    SECURITY_CODE_EXPIRED("security.code.expired"),
    INCORRECT_OLD_PASSWORD("incorrect.old.password"),
    CULTURE_ALREADY_EXISTS("culture.already.exists"),
    PLEASE_ADD_ALL_REQUIRED_PARAMETERS("please.add.all.required.parameters"),
    CULTURE_NOT_FOUND("culture.not.found"),
    AGREEMENT_NOT_FOUND("agreement.not.found"),
    EMAIL_ALREADY_USED("email.already.used"),
    ADDRESS_NOT_FOUND("address.not.found"),
    CATEGORY_NOT_FOUND("category.not.found"),
    CATEGORY_ALREADY_EXISTS("category.already.exists"),
    FILE_NOT_FOUND("file.not.found"),
    FILE_SAVE_ERROR("file.save.error"),
    PRODUCT_NOT_FOUND("product.not.found"),
    CART_ITEM_NOT_FOUND("cart.item.not.found"),
    ORDER_NOT_FOUND("order.not.found"),
    CART_EMPTY("cart.empty"),
    PAYMENT_NOT_FOUND("payment.not.found"),
    ;

    private final String key;

    MessageKey(String key) { this.key = key; }

}
