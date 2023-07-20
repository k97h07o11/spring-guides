package com.example.jwt.controller;

import com.example.jwt.dto.JoinRequestDto;
import com.example.jwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public void join(
            @RequestBody JoinRequestDto joinRequestDto
    ) {
        memberService.join(joinRequestDto);
    }
}
