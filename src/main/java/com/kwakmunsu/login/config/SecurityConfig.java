package com.kwakmunsu.login.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Spring Security를 활성화하는 어노테이션입
public class SecurityConfig {

    @Bean //암호화를 진행할 메소드
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 스프링 컨테이너에 의해 관리되는 자바 객체
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 인가 작업: 아래 경로에 대한 권한 설정
        // authorizeHttpRequests = HTTP 요청에 대한 인가(Authorization)**를 설정하는 메서드
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login","/join","/joinProc").permitAll() //모두 접근 허용
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()   //그 외 경로

        );


        // formLogin() = 폼 기반 로그인 활성화
        // loginPage() = 사용자가 인증되지 않은 상태에서 보호된 리소스에 접근하면,
        // Spring Security는 사용자를 /login 경로로 리다이렉트하여 로그인 페이지로 안내합니다.
        http.formLogin(auth -> auth.loginPage("/login") // 해당 경로로 리다이렉션
                .loginProcessingUrl("/loginProc") // 로그인 폼에서 처리된 데이터를 해당 경로로 보냄 즉 로그인 처리 요청 경로
                .permitAll()
        );

        // csrf라는 걸 security가 자동으로 해주는데 이러면 로그인할때 csrf 토큰도 같이 보내줘야되는데 지금은 사용안하는 잠시 비활성
//        http.csrf(auth -> auth.disable());
        // 다중 로그인 설정
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // 하나의 아이디에 대한 다중 로그인 허용 개수
                        .maxSessionsPreventsLogin(true)); // 초과시 새로운 로그인 차단

        // 세션 고정 보호
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());


        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        return http.build();
    }
}

// 정리
// security는 기본으로 로그인 페이지를 제공하는데 사용자가 인증되지 않은 상태에서 보호된 리소스에 접근할 경우 기본 로그인 페이지가 제공되지 않음.
// 그래서 오류 페이지가 아닌 커스텀 로그인 페이지를 만들어 그 페이지로 리다이렉션시킴
// 사용자가 로그인 폼을 제출하면, loginProcessingUrl로 설정된 경로로 POST 요청이 발생. 이 요청이 Spring Security의 필터에 의해 가로채져 사용자 인증이 진행.