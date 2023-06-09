package com.project.controllers.member;

import com.project.models.member.social.ProfileResult;
import com.project.models.member.social.SocialLogin;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/social")
@RequiredArgsConstructor
public class SocialLoginController {

    private final SocialLogin socialLogin;

    @GetMapping("/login")
    public String login(String code, HttpSession session) {
        if (code == null || code.isBlank()) {
            return "redirect:/member/login";
        }

        ProfileResult profileResult = socialLogin.getProfile(code);
        if (profileResult == null) {
            return "redirect:/member/login";
        }

        session.setAttribute("kakao", profileResult);

        // 이미 가입된 카카오 계정이면 로그인 처리
        // 가입이 안된 카카오 계정이면 회원 가입

        return "redirect:/member/join";
    }

}
