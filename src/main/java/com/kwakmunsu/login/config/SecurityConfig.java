package com.kwakmunsu.login.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
// 아래 경로에 대한 권한 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/login").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()   //그 외 경로

        );
        return http.build();
    }
}
