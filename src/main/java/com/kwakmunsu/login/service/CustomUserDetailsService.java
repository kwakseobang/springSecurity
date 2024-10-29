package com.kwakmunsu.login.service;

import com.kwakmunsu.login.dto.CustomUserDetails;
import com.kwakmunsu.login.entity.UserEntity;
import com.kwakmunsu.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private  final UserRepository userRepository;

    //호출 시 앞 단에서 사용자가 로그인하면 시큐리티가 검증을 위해서 username을 인자에 넣어줌
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);

        if (user != null) {
            return new CustomUserDetails(user);
        }
        return null;
    }
}
