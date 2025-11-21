package com.test.security.service;

import com.test.security.dto.MemberDTO;
import com.test.security.entity.Member;
import com.test.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    public void join(MemberDTO dto) {
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();
        memberRepository.save(member);
    }
}
