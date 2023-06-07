package com.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 가입 엔티티
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(indexes={
//        @Index(name="idx_member_userNm", columnList = "userNm"),
//        @Index(name="idx_member_email", columnList = "email")
//})
public class Member extends BaseEntity{

    @Id @GeneratedValue
    private Long id; // 회원번호
    @Column(length = 30, nullable = false, unique = true)
    private String userId; // 회원 아이디
    @Column(length = 55, nullable = false)
    private String userPw; // 회원 비밀번호
    @Column(length = 20, nullable = false)
    private String userNm; // 회원 이름
    @Column(length = 20)
    private String PhoneNum; // 회원 휴대전화 번호
    @Column(length = 50)
    private String email; // 회원 이메일
    @Column(length = 100)
    private String address; // 주소

}
