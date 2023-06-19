package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_cateCd_desc", columnList = "cateCd DESC")
})
public class Product extends BaseEntity {

    @Id @GeneratedValue
    private Long pNo; // 상품번호

    @Column(length = 60, nullable = false)
    private String gid;

    @ManyToOne
    @JoinColumn(name="cateCd")
    private Category category; // 상품분류

    @Column(nullable = false)
    private String pName; // 상품명

    private long consumerPrice; // 소비자가
    private long salePrice; // 판매가
    private long stock;// 재고

    private String shortDescription;

    @Lob
    private String description;

    @Transient
    private FileInfo mainImage; // 메인 이미지

    @Transient
    private FileInfo listImage; // 목록 이미지

    @Transient
    private List<FileInfo> editorImages; // 에디터 이미지

}
