package com.kwakmunsu.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class UserService {

    public String getUserId() throws Exception {
        // security를 통한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // 현재 로그인한 사용자의 ID 반환
    }


    // 아래 로직을 통해 유저의 권한을 가져와서 사용. 특정 권한이 있어야만 삭제 가능하다던지 그런 권한 필터가 필요한 곳에 사용한다.
    public String getUserRole() throws Exception {
        // security를 통한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (!authorities.isEmpty()) {
            // 권한 중 첫 번째 권한 반환
            return authorities.iterator().next().getAuthority();
        }
        return null; // 권한이 없으면 null 반환
    }
}
