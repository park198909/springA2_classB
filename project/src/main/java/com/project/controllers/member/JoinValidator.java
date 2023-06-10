package com.project.controllers.member;

import com.project.commons.validators.MobileValidator;
import com.project.models.member.social.ProfileResult;
import com.project.repositories.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator, MobileValidator {

    private final MemberRepository repository;
    private final HttpSession session;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm joinForm = (JoinForm)target;
        /**
         * 1. 아이디 중복 여부
         * 2. 비밀번호, 비밀번호 확인 일치
         * 3. 휴대전화번호 검증
         */
        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();

        ProfileResult profileResult = (ProfileResult) session.getAttribute("kakao");

        // 0. 카카오 회원가입이 아닌 경우 비밀번호 필수 여부 체크
        if (profileResult == null && (userPw == null || userPw.isBlank())) {
            errors.rejectValue("userPw", "NotBlank");
        }

        if (profileResult == null && (userPwRe == null || userPwRe.isBlank())) {
            errors.rejectValue("userPwRe", "NotBlank");
        }

        // 1. 아이디 중복 여부
        if (userId != null && !userId.isBlank() && repository.exists(userId)) {
            errors.rejectValue("userId", "Duplicate.joinForm.userId");
        }

        // 2. 비밀번호, 비밀번호 확인 일치여부
        if (userPw != null && !userPw.isBlank()
                && userPwRe != null && !userPwRe.isBlank() && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "Incorrect.joinForm.userPwRe");
        }

        // 3. 휴대전화번호 검증(선택)
        if (mobile != null && !mobile.isBlank()) {
            if (!mobileCheck(mobile)) {
                errors.rejectValue("mobile", "Validation.mobile");
            }

            mobile = mobile.replaceAll("\\D", "");
            joinForm.setMobile(mobile);
        }
    }
}
