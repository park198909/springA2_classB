package org.koreait.controllers.members;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.member.MemberSaveService;
import org.koreait.models.member.MemberSearch;
import org.koreait.models.member.MemberSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSaveService saveService;
    private final JoinValidator joinValidator;
    private final MemberSearchService searchService;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm, Model model) {
        commonProcess(model);
        return "member/join";
    }

    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors, Model model) {
        commonProcess(model);

        joinValidator.validate(joinForm, errors);

        if (errors.hasErrors()) {
            return "member/join";
        }

        saveService.save(joinForm);

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {

        return "member/login";
    }

    @GetMapping("/find_id")
    public String findId(@ModelAttribute MemberSearch memberSearch, Model model) {
        commonProcess(model, "아이디 찾기");

        return "member/find_id";
    }

    @PostMapping("/find_id")
    public String findIdPs(@ModelAttribute MemberSearch memberSearch, Model model) {
        commonProcess(model, "아이디 찾기");

        String userNm = memberSearch.getUserNm();
        String mobile = memberSearch.getMobile();

        String script = null;
        try {
            String userId = searchService.idSearch(userNm, mobile) + "입니다.";
            script = String.format("Swal.fire('찾으시는 아이디는', '%s', 'success').then(function() {location.href='/member/login';})", userId);
        } catch (Exception e) {
            script = String.format("Swal.fire('아이디를 찾을 수 없습니다.', '', 'error').then(function() {history.go(-1);})");
        }

        model.addAttribute("script", script);
        return "commons/sweetalert_script";
    }

    private void commonProcess(Model model) {
        model.addAttribute("pageTitle", "회원가입");
    }

    private void commonProcess(Model model, String title) {
        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);
    }
}
