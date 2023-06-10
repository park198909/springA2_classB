package com.project.entities;

import com.project.commons.constants.MemberType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Member extends BaseEntity{
    @Id @GeneratedValue
    private Long userNo;

    @Column(length = 40, nullable = false, unique = true)
    private String userId;

    @Column(length = 65)
    private String userPw;

    @Column(length = 40, nullable = false)
    private String userNm;

    @Column(length = 100)
    private String email;

    @Column(length = 11)
    private String mobile;

    @Column(length = 10)
    private String zipcode;
    @Column(length = 100)
    private String address;
    @Column(length = 100)
    private String addressSub;

    @Column(length = 10)
    private String socialChannel;

    @Column(length = 40)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MemberType type = MemberType.USER;
}
