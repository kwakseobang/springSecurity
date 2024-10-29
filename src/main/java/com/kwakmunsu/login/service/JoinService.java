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

    private  final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUp(JoinDTO joinDTO) throws  Exception{

        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());

        if (isUser) {
            throw new Exception("사용자가 이미 존재합니다.");
        }

        //DTO -> entity로 전환
        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        //비밀번호는 암호화 해야됨
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
