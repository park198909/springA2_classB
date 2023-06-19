package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.commons.constants.CartStatus;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Cart extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="pNo")
    private Product product;

    @Column(name="mode_", length=10, nullable = false)
    private String mode = "Cart";

    private long productCnt; // 구매 수량

    private int guestId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CartStatus status = CartStatus.READY;
}
