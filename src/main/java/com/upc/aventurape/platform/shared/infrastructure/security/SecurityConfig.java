package com.upc.aventurape.platform.shared.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean("apiFilterChain")
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // deshabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",                        // tu redirección
                                "/swagger-ui/**",          // Swagger UI
                                "/v3/api-docs/**",         // Docs de OpenAPI
                                "/actuator/**",           // opcional, si usas Spring Actuator
                                "/swagger-ui/index.html#/"
                        ).permitAll()                // permiten acceso sin auth
                        .anyRequest().authenticated() // el resto requiere login
                );

        return http.build();
    }
}
