package com.project.commons.validators;

public interface PasswordValidator {
    // 비밀번호 복잡성 체크

    // 알파벳 체크
    // false : 소문자 + 대문자가 반드시 포함되는 패턴
    // true : 대소문자 상관 없이 포함되는 패턴

    default boolean alphaCheck(String password, boolean caseIncentive) {
        // 대소문자 구분 없이 체크
        if(caseIncentive) {
            return password.matches("[a-zA-Z]+");
        }
        // 대소문자 구분 하여 체크
        return password.matches("[a-z]+") && password.matches("[A-Z]");
    }

    default boolean numberCheck(String password) { // 숫자 체크

        return password.matches("\\d+"); // [0-9]+
    }

    default boolean specialCharsCheck(String password) {
        return password.matches("['~!#$@%^&*()_+=-]+"); // 특수문자 체크
    }
}
