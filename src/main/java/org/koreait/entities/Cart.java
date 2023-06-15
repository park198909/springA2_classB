package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Builder @Data
@NoArgsConstructor @AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    @Column(name="product_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.member = member;
        return cart;
    }
}
