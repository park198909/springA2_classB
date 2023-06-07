package com.project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Member extends BaseEntity{
    @Id @GeneratedValue
    private Long userNo; //회원번호

    @Column(length = 40, nullable = false, unique = true)
    private String userId; //아이디

    @Column(length = 65, nullable = false)
    private String userPw; //비밀번호

    @Column(length = 40, nullable = false)
    private String userNm; //회원명

    @Column(length = 100)
    private String email; //이메일

    @Column(length = 11)
    private String mobile; //휴대전화번호

    @Column(length = 250)
    private String address; //주소
}
