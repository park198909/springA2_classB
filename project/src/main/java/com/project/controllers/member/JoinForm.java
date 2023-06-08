package com.project.controllers.member;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
public class JoinForm {
    @NotBlank
    @Size(min = 6, max = 16)
    private String userId;

    @NotBlank
    @Size(min = 8)
    private String userPw;

    @NotBlank
    private String userPwRe;

    @NotBlank
    private String userNm;

    @Email
    private String email;

    private String mobile;

    private String zipcode;
    private String address;
    private String addressSub;

    @AssertTrue
    private boolean agree;

}
