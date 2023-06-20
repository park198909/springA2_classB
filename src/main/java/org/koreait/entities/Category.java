package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(indexes={
        @Index(name="idx_listOrder_desc", columnList = "listOrder DESC"),
        @Index(name="created_at_desc", columnList = "createdAt DESC")
})
public class Category extends BaseEntity {
    @Id @Column(length = 20)
    private String cateCd; // 분류 코드

    @Column(name="is_use")
    private boolean use; // 사용 여부

    @Column(length = 60, nullable = false)
    private String cateNm; // 분류명

    private Long listOrder; // 진열 순서 - 숫자가 높을수록 진열 가중치 부여
}
