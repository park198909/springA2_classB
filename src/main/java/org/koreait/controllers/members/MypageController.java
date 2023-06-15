package org.koreait.controllers.members;


import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class MypageController {

    private final MemberRepository memberRepository;

    // 회원정보 조회 페이지
    @GetMapping("/info")
    public String info(ModelMap modelMap , Principal principal) {

        String userId = principal.getName();
        Member member = memberRepository.findByUserId(userId);
        modelMap.addAttribute("member",member);

        return "/mypage/info";
    }
    @GetMapping("/infoupdate")
    public String infoupdate() {
        return "/mypage/infoupdate";
    }

    @GetMapping("/cart")
    public String cart() {
        return "/mypage/cart";
    }

    @GetMapping("/orderlist")
    public String orderlist() {
        return "/mypage/orderlist";
    }



}
