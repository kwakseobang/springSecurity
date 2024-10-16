package com.kwakmunsu.login.repository;

import com.kwakmunsu.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


//종속될 entity와 id값을 넣어줌 id값의 자료형을 넣어주는데 객체 타입으로 넣어줌 int 그대로 넣어주면 오류 발생
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
