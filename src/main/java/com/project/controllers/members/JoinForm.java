package com.project.controllers.members;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinForm {
    @NotBlank @Size(min=6, max=30)
    private String userId; // 회원 아이디
    @NotBlank @Size(min=8)
    private String userPw; // 비밀번호
    @NotBlank
    private String userPwRe; // 비밀번호 확인
    @NotBlank
    private String userNm; // 회원 명
    @NotBlank @Email
    private String email; // 이메일
    private String phoneNum; // 전화번호
    private String address; // 주소

}
