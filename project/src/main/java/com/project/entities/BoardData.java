package com.project.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardData extends BaseEntity{
    @Id @GeneratedValue //자동 증감 번호
    private Long id; //게시글 번호

    @Column(nullable = false)
    private String subject; //게시글 제목

    @Lob
    @Column(nullable = false)
    private String content; //게시글 내용

    @Column(length = 40, nullable = false)
    private String poster; //작성자

    @ManyToOne(fetch = FetchType.LAZY)//지연 로딩
    @JoinColumn(name = "user_no")
    @ToString.Exclude
    private Member member;
}
