package com.project.controllers.members;

import com.project.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        JoinForm joinForm = (JoinForm) target;
        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();

        // 아이디 중복 여부
        if(userId != null && !userId.isBlank() && memberRepository.exists(userId)) {
            errors.rejectValue("userId", "Validation.duplicate.userId");
        }

        // 비밀번호, 비밀번호 확인 일치 여부
        if(userPw != null && !userPw.isBlank() && userPwRe !=null && userPwRe.isBlank() && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "Validation.incorrect.userPwRe");
        }

    }
}
