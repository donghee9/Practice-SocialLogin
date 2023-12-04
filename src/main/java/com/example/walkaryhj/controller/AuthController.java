package com.example.walkaryhj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        // 로그인 성공 후 처리 로직
        return "loginSuccess";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        // 로그인 실패 후 처리 로직
        return "loginFailure";
    }
}
