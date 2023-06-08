package com.project.controllers.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm, Model model) {
        model.addAttribute("addScript", new String[]{"member/join"});

        return "member/join";
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
