package com.project.controllers;

import com.project.controllers.members.JoinForm;
import com.project.models.member.MemberSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSaveService memberSaveService;

    @GetMapping("/join")
    public String join(Model model) {
//
//        String mem = "member";
//        model.addAttribute("member", mem);

        return "member/join";
    }
    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {

        if(errors.hasErrors()) {
            return "member/join";
        }

        memberSaveService.save(joinForm);

        return "redirect:/member/login";
    }

}