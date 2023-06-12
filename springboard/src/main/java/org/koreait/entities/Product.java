package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10, nullable = false)
    private String productNm;

    @Column(length = 100)
    @Lob
    private String detail;

    @Column(length = 20, nullable = false)
    private Integer price;
}
