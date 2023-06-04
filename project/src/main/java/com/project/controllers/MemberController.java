package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join")
    public String join(Model model) {

        String mem = "member";
        model.addAttribute("member", mem);

        return "member/join";
    }
}
