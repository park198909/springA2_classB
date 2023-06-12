package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Product extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 10, nullable = false)
    private String productNm;

    @Column(length = 100)
    @Lob
    private String detail;

    @Column(length = 20, nullable = false)
    private Integer price;
}
