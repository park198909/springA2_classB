package com.project.controllers.member;

import com.project.models.member.social.ProfileResult;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final HttpSession session;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm, Model model) {

        ProfileResult profileResult = (ProfileResult)session.getAttribute("kakao");
        if (profileResult != null) {
            joinForm.setUserNm(profileResult.getProperties().getNickname());
        }

        model.addAttribute("addScript", new String[]{"member/join"});

        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {



        return "redirect:member/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, HttpSession session, @CookieValue(required = false) String savedId) {
        if (savedId != null) {
            loginForm.setUserId(savedId);
            loginForm.setSaveId(true);
        }

        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            loginForm.setUserId(userId);
        }
        return "member/login";
    }
}
