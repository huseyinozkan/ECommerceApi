package com.example.e_commerce_rest_api.utils.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.security")
public class AppSecurityProperties {
    private long accessTokenExpirationMinute;
    private long refreshTokenExpirationMinute;
    private String secretKey;
}
