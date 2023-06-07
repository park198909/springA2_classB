package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class BoardData extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length=40, nullable = false)
    private String poster;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_no")
    @ToString.Exclude
    private Member member;
}
