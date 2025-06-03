package com.tamnafriends.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 토큰 체크 끔(실험/테스트용)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/choose.html", "/signin.html", "/css/**", "/js/**", "/images/**"
                        ).permitAll() // 정적 리소스 허용
                        .anyRequest().permitAll() // 모든 API 임시 허용(나중엔 authenticated()로)
                );
        return http.build();
    }
}
