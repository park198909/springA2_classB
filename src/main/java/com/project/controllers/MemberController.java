package com.project.controllers;

<<<<<<< HEAD
import com.project.controllers.members.JoinForm;
import com.project.controllers.members.JoinValidator;
import com.project.models.member.MemberSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
=======
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> 14efcc179c75639ba70c7ee47c9468e6cc8e984b
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
<<<<<<< HEAD
@RequiredArgsConstructor
public class MemberController {

    private final MemberSaveService memberSaveService;
    private final JoinValidator joinValidator;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm, Model model) {
//
//        String mem = "member";
//        model.addAttribute("member", mem);

        return "member/join";
    }
    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors) {

        joinValidator.validate(joinForm, errors);

        if(errors.hasErrors()) {
            return "member/join";
        }

        memberSaveService.save(joinForm);

        return "redirect:/member/login";
    }
=======
public class MemberController {

    @GetMapping("/join")
    public String join(Model model) {

        String mem = "member";
        model.addAttribute("member", mem);

        return "member/join";
    }
>>>>>>> 14efcc179c75639ba70c7ee47c9468e6cc8e984b

}