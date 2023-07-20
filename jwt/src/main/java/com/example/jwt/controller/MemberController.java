package com.example.jwt.controller;

import com.example.jwt.dto.JoinRequestDto;
import com.example.jwt.security.UserDetailsImpl;
import com.example.jwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final JwtEncoder encoder;

    @PostMapping("/members")
    public void join(
            @RequestBody JoinRequestDto joinRequestDto
    ) {
        memberService.join(joinRequestDto);
    }

    @PostMapping("/auth/token")
    public String token(
            Authentication authentication
    ) {
        Instant now = Instant.now();

        long expiry = 36000L;

        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getMember().getId();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(String.valueOf(userId))
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
