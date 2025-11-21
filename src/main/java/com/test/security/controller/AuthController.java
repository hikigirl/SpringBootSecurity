package com.test.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    //403 forbidden 예외처리 페이지
    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }
    //로그인
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //인증 티켓
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //로그아웃
            new SecurityContextLogoutHandler().logout(req, resp, auth);
        }
        return "redirect:/";
    }
}
