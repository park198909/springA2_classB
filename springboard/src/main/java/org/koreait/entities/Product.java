package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Product extends BaseEntity{

    @Id @GeneratedValue
    private Long pNo; // 상품번호

    @Column(length = 60, nullable = false)
    private String gid;

    @Column(length = 45)
    private String cateCd; // 상품분류


    @Column(nullable = false)
    private String pName;

    private Long consumerPrice;
    private Long salePrice;
    private Long stock;

    private String shortDescription;

    @Lob
    private String description;


}
