package com.example.e_commerce_rest_api.utils.config;


import com.example.e_commerce_rest_api.utils.jwt.CustomAccessDeniedHandler;
import com.example.e_commerce_rest_api.utils.jwt.CustomAuthenticationEntryPoint;
import com.example.e_commerce_rest_api.utils.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String BASE_PATH = "/ecommerce/api/v1";
    private static final String[] PUBLIC_ENDPOINTS = {
            "/swagger-ui/**", // http://localhost:8082/swagger-ui/index.html
            "/v3/api-docs/**",
            BASE_PATH + "/auth/login",
            BASE_PATH + "/auth/refresh-token",
            BASE_PATH + "/auth/send-otp-code",
            BASE_PATH + "/auth/register",
            BASE_PATH + "/auth/forgot-password"
    };

    private static final String[] GET_PUBLIC_ENDPOINTS = {
            BASE_PATH + "/culture/**",
            BASE_PATH + "/resource/**",
            BASE_PATH + "/agreement/current"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, GET_PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
