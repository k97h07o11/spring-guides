package com.example.jwt.dto;

import com.example.jwt.entity.Member;
import lombok.Getter;

@Getter
public class JoinRequestDto {

    String username;

    String password;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .build();
    }
}
