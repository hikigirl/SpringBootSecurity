package com.test.security.controller;

import com.test.security.dto.MemberDTO;
import com.test.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //주입
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/joinok")
    public String joinok(MemberDTO dto){
        System.out.println(dto);
        memberService.join(dto);
        return "redirect:/login";
    }
}
