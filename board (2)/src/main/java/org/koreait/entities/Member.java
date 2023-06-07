package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.commons.constants.MemberType;

import java.util.ArrayList;
import java.util.List;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private Long userNo;

    @Column(length=40, nullable = false, unique = true)
    private String userId;

    @Column(length=65, nullable = false)
    private String userPw;

    @Column(length=40, nullable=false)
    private String userNm;

    @Column(length=100)
    private String email;

    @Column(length=11)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private MemberType type = MemberType.USER;

    @OneToMany(mappedBy = "member")
    private List<BoardData> boardDatas = new ArrayList<>();
}