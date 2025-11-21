package com.test.security.service;

import com.test.security.dto.CustomUserDetails;
import com.test.security.entity.Member;
import com.test.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    // 로그인 페이지 -> 아이디, 암호 입력 -> 로그인 버튼 클릭
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //select * from member where username = ?
        Optional<Member> member = memberRepository.findById(username);
        if (member.isPresent()) {
            return new CustomUserDetails(member.get()); //인증 객체
        } //로그인 성공
        return null; //로그인 실패
    }
}
