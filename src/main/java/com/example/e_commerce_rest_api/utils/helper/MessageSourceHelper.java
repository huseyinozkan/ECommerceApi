package com.example.e_commerce_rest_api.utils.helper;

import com.example.e_commerce_rest_api.enums.MessageKey;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceHelper {
    private final MessageSource messageSource;

    public MessageSourceHelper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(MessageKey key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key.getKey(), null, locale);
    }

    public String getMessage(MessageKey key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key.getKey(), args, locale);
    }
}
