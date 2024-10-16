package com.kwakmunsu.login.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// entity는 DB의 테이블과 매핑되는 클래스
@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가
    private int id;

    private  String username;
    private String password;

    private String role;
}

