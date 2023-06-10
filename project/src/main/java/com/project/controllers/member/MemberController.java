package com.project.controllers.member;

import com.project.commons.MemberUtils;
import com.project.models.member.JoinService;
import com.project.models.member.MemberInfo;
import com.project.models.member.social.ProfileResult;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Log
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final JoinValidator joinValidator;
    private final JoinService joinService;
    private final MemberUtils memberUtils;
    private final HttpSession session;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm, Model model) {

        ProfileResult profileResult = (ProfileResult)session.getAttribute("kakao");
        if (profileResult != null) {
            joinForm.setUserNm(profileResult.getProperties().getNickname());
        }

        model.addAttribute("addScript", new String[] { "member/join"} );

        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {

        joinValidator.validate(joinForm, errors);

        if (errors.hasErrors()) {
            return "member/join";
        }

        joinService.join(joinForm);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, HttpSession session, @CookieValue(required=false) String savedId) {
        if (savedId != null) {
            loginForm.setUserId(savedId);
            loginForm.setSaveId(true);
        }

        String userId = (String)session.getAttribute("userId");
        if (userId != null) {
            loginForm.setUserId(userId);
        }

        return "member/login";
    }

    @GetMapping("/ex")
    @ResponseBody
    public void ex() {
        MemberInfo memberInfo = memberUtils.getMember();
        log.info(memberInfo.toString());
        /**
         MemberInfo memberInfo = (MemberInfo)SecurityContextHolder
         .getContext().getAuthentication().getPrincipal();

         log.info(memberInfo.toString());
         */
    }
    /*
    public void ex(@AuthenticationPrincipal MemberInfo memberInfo) {
        log.info(memberInfo.toString());
    }
    */
    /**
     public void ex(Principal principal) {
     String userId = principal.getName();
     log.info(userId);
     }
     */
}