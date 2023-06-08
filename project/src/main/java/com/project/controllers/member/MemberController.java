package com.project.controllers.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

        @GetMapping("/join")
        public String join(@ModelAttribute JoinForm joinForm, Model model) {
            model.addAttribute("addScript", new String[] {"member/join"} );

            return "member/join";
        }

}
