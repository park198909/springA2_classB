package org.koreait.controllers.members;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.CartItemDto;
import org.koreait.entities.Member;
import org.koreait.models.cart.CartService;
import org.koreait.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class MypageController {

    private final MemberRepository memberRepository;
    private final CartService cartService;

    // 회원정보 조회 페이지
    @GetMapping("/info")
    public String info(Model model , Principal principal) {

        String userId = principal.getName();
        Member member = memberRepository.findByUserId(userId);
        model.addAttribute("member",member);

        return "/mypage/info";
    }

    // 정보 업데이트
//    @GetMapping("/infoupdate")
//    public String infoupdate(@PathVariable("id") long id, @ModelAttribute JoinForm joinForm, Model model) {
//        commonProcess(model);
//        return "/mypage/infoupdate";
//    }
    @GetMapping("/infoupdate")
    public String infoupdate() {
        return "/mypage/infoupdate";
    }

//    @PostMapping("/infoupdate")
//    public String infoupdate(Principal principal, Model model) {
//        String userId = principal.getName();
//        Member member = memberRepository.findByUserId(userId);
//        model.addAttribute("member", member);
//
//        return "/mypage/infoupdate";
//    }

//    @GetMapping("/cart")
//    public String cart() {
//        return "/mypage/cart";
//    }

    @PostMapping("/cart")
    public ResponseEntity cart(@RequestBody @Valid CartItemDto cartItemDto, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long cartItemId;

        try {
            cartItemId = cartService.addCart(cartItemDto, principal.getName());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    @GetMapping("/orderlist")
    public String orderlist() {
        return "/mypage/orderlist";
    }


    private void commonProcess(Model model) {
        model.addAttribute("pageTitle", "회원정보 수정");
    }

}
