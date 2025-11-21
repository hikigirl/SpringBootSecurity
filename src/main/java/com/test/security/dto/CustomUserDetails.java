package com.test.security.dto;

import com.test.security.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {
    //사용자 추가 정보 저장용
    //- 이전 수업(UserDTO)
    //- 현재 수업(Member Entity)
    private Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {

        //계정 만료
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        //잠금 상태
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        //자격 증명(암호) 만료
        return true;
    }

    @Override
    public boolean isEnabled() {

        //사용 유무
        return true                                                                                                                                 ;
    }

}
