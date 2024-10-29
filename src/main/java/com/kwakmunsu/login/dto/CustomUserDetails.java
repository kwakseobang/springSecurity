package com.kwakmunsu.login.dto;

import com.kwakmunsu.login.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


//사용자 인증 정보
public class CustomUserDetails implements UserDetails {


    private UserEntity userEntity;
    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    // 사용자 툭정 권한 반환 보통 사용자의 역할 반환 ADMIN, USER ...
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collection;
    }



    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }
    // 계정 인증 기간 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
    // 계정 잠겨있는지 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}
