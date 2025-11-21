package com.test.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    //public을 생략 가능하다.
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //접근 권한 제어하기(URI 허가 정책)
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
        );

        return http.build();
    }
}
