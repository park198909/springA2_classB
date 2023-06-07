package org.koreait.configs;

import org.koreait.models.member.LoginFailureHandler;
import org.koreait.models.member.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin(f -> f
						.loginPage("/member/login")
						.usernameParameter("userId")
						.passwordParameter("userPw")
						//.defaultSuccessUrl("/")
						//.failureUrl("/member/login")
						.failureHandler(new LoginFailureHandler())
						.successHandler(new LoginSuccessHandler())
				)
				.logout(f -> f
						.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
						.logoutSuccessUrl("/member/login")
				);

		http.authorizeHttpRequests(f -> f
				.requestMatchers("/mypage/**").authenticated()
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().permitAll()
		);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
