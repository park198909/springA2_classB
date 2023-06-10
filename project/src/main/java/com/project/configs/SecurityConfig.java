package com.project.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.project.models.member.LoginFailureHandler;
import com.project.models.member.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(f -> f
                        .loginPage("/member/login")
                        .usernameParameter("userId")
                        .passwordParameter("userPw")
                        //.defaultSuccessUrl("/") // 로그인 성공시 유입되는 URL
                        //.failureForwardUrl("/member/login") // 로그인 실패시 유입되는 URL
                        .failureHandler(new LoginFailureHandler()) // 로그인 실패시 유입되는 URL 을 직접 정의
                        .successHandler(new LoginSuccessHandler()) // 로그인 성공시 유입되는 URL 을 직접 정의
                )
                .logout(f -> f
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/member/login") // 로그아웃 성공시 URL
                );

        http.authorizeHttpRequests(f -> f
                .requestMatchers("/mypage/**").authenticated() // 로그인한 회원만 접근 가능한 URL
                .requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자만 접근 가능한 페이지
                .anyRequest().permitAll()
        );

        http.exceptionHandling(f -> f
                .authenticationEntryPoint((req, res, e) -> {
                            String URI = req.getRequestURI(); // 현재 접속한 경로
                            if (URI.indexOf("/admin") != -1) { // 관리자 - 401
                                // 401 에러 페이지
                                res.sendError(401, "NOT AUTHORIZED");
                                return;
                            }

                            // 회원 전용 페이지 - 로그인 페이지
                            String url = req.getContextPath() + "/member/login";
                            res.sendRedirect(url);
                        }
                ));

        return http.build();
    }

    @Bean    // 스프링 시큐리티 미적용 URL 패턴
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/upload/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}