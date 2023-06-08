package com.project.controllers.member;

import com.project.models.member.social.SocialLogin;
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

    @ResponseBody
    @GetMapping("/login")
    public void login(String code) {
        if (code == null || code.isBlank()) {
            //return "redirect:/member/login";
        }

        socialLogin.getProfile(code);
    }
}
