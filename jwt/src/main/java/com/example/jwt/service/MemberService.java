package com.example.jwt.service;

import com.example.jwt.dto.JoinRequestDto;
import com.example.jwt.entity.Member;
import com.example.jwt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinRequestDto joinRequestDto) {
        if (!validateUsername(joinRequestDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Member member = joinRequestDto.toEntity();
        member.encodePassword(passwordEncoder);
        memberRepository.save(member);
    }

    public boolean validateUsername(String username) {
        return !memberRepository.existsByUsername(username);
    }
}
