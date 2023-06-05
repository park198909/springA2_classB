package com.project.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  // 시큐리티 무력화 - url
        return http.build();
    }

    public WebSecurityCustomizer webSecurityCustomizer() {  // 시큐리티 무력화 - 정적경로
        return w->w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**");
    }
}