package com.project.controllers.member;

import com.project.commons.validators.MobileValidator;
import com.project.models.member.social.ProfileResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class JoinValidator implements Validator, MobileValidator {

    private HttpSession session;

    private String userId;
    private String userPw;
    private String userPwRe;
    private String mobile;

    ProfileResult profileResult = (ProfileResult) session.getAttribute("kakao");

//    // 0. 카카오 회원가입이 아닌 경우 비밀번호 필수 여부 체크
//    if (userPw == null || userPw.isBlank()) {
//        errors.rejectValue("userPw", "NotBlank");
//    }
//
//    if (userPwRe == null || userPwRe.isBlank()) {
//        errors.rejectValue("userPw", "NotBlank");
//    }

    @Override
    public boolean supports(Class<?> clazz) {
        return JoinForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinForm joinForm = (JoinForm)target;

        /** 1. 아이디 중복여부
         *  2. 비밀번호 일치여부
         *  3. 휴대폰 번호 검증 */

        String userId = joinForm.getUserId();
        String userPw = joinForm.getUserPw();
        String userPwRe = joinForm.getUserPwRe();
        String mobile = joinForm.getMobile();

//        /** 1. 아이디 중복여부 */
//        if (userId != null && userId.isBlank() && repository.exists(userId)) {
//            errors.rejectValue("userId", "Duplicate.joinForm.userId");
//        }

        /** 2. 비밀번호 일치여부 */
        if (userPw != null && userPw.isBlank()
                && userPwRe != null && userPwRe.isBlank() && !userPw.equals(userPwRe)) {
            errors.rejectValue("userPwRe", "Incorrect.joinForm.userPwRe");
        }

        /** 3. 휴대폰 번호 검증 (필수항목 X, 있을때만 검증) */
        if (mobile != null && mobile.isBlank()) {
            if (!mobileCheck(mobile)) {
                errors.rejectValue("mobile", "Validation.mobile");
            }

            mobile = mobile.replaceAll("\\D", "");
            joinForm.setMobile(mobile);
        }
    }
}
