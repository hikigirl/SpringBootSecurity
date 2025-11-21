package com.test.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    //public을 생략 가능하다.
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //접근 권한 제어하기(URI 허가 정책)
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/join", "joinok").permitAll()
                .requestMatchers("/member").hasRole("MEMBER") //ROLE_MEMBER에서 ROLE_을 생략 가능
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated() //위에 지정한 페이지 이외의 나머지 URI에 대한 권한 막기
        );
        //개발 도중에 CSRF 토큰 비활성화
        //http.csrf(auth -> auth.disable());

        //커스텀 로그인 페이지
        http.formLogin(form -> form
                .loginPage("/login") //로그인 페이지
                .loginProcessingUrl("/loginok") //로그인 처리 페이지, form 태그의 action 값
        );

        //예외 처리(권한&인증 관련, 401, 403)
        http.exceptionHandling(e -> e
                .authenticationEntryPoint((request, response, authException) -> {
                    //401 Unauthorized: 인증 실패
                    // 익명 사용자가 인증 사용자 URL에 접속
                    response.sendRedirect("/login");
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    //403 Forbidden: 권한 부족? 허가 실패
                    // 인증했지만 권한(Role)이 없어서 발생
                    response.sendRedirect("/denied");
                })
        );

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*
    @Bean
    UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserDetails dog = User.builder()
                .username("dog")
                .password(bCryptPasswordEncoder().encode("1111"))
                .roles("MEMBER")
                .build();
        UserDetails tiger = User.builder()
                .username("tiger")
                .password(bCryptPasswordEncoder().encode("1111"))
                .roles("ADMIN", "MEMBER")
                .build();
        //인메모리
        return new InMemoryUserDetailsManager(dog, tiger);
    }
    */
}
