package com.kwakmunsu.login.service;


import com.kwakmunsu.login.dto.JoinDTO;
import com.kwakmunsu.login.entity.Role;
import com.kwakmunsu.login.entity.UserEntity;
import com.kwakmunsu.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


//회원가입
@RequiredArgsConstructor
@Service
public class JoinService {

    final private UserRepository userRepository;

    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUp(JoinDTO joinDTO) {

        //db에 동일한 username을 가진 회원이 존재하는지 검증 로직 구현

        //DTO -> entity로 전환
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        //비밀번호는 암호화 해야됨
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole(Role.USER.name());

        userRepository.save(data);
    }
}
