package com.project.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> 14efcc179c75639ba70c7ee47c9468e6cc8e984b
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  // 시큐리티 무력화 - url
        return http.build();
    }

<<<<<<< HEAD
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {  // 시큐리티 무력화 - 정적경로
        return w->w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
=======
    public WebSecurityCustomizer webSecurityCustomizer() {  // 시큐리티 무력화 - 정적경로
        return w->w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**");
    }
>>>>>>> 14efcc179c75639ba70c7ee47c9468e6cc8e984b
}