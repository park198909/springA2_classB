package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Cart extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="pNo")
    private Product product;

    @Column(length=10, nullable = false)
    private String mode = "Cart";

    private long productCnt; // 구매 수량
}
