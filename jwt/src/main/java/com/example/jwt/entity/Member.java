package com.example.jwt.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Builder
    private Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
