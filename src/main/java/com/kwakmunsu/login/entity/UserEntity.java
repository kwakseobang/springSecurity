package com.kwakmunsu.login.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// entity는 DB의 테이블과 매핑되는 클래스
@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동
    private int id;

    @Column(unique = true) // 중복 방지. 유일한 값
    private  String username;
    private String password;

    private String role;
}

