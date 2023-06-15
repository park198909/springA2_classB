package org.koreait.models.cart;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.*;
import org.koreait.repositories.CartItemRepository;
import org.koreait.repositories.CartRepository;
import org.koreait.repositories.MemberRepository;
import org.koreait.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public Long addCart(CartItemDto cartItemDto, String id) {
        Member member = memberRepository.findByUserId(id);
        Cart cart = cartRepository.findById(member.getUserId());

        // 장바구니가 존재하지 않으면 생성
        if(cart == null ) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        Product product = productRepository.findById(cartItemDto.getId()).orElseThrow(EntityNotFoundException::new);
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getPNo());

        // 해당 상품이 장바구니에 존재하지 않는다면 생성 후 추가
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart,product,cartItemDto.getCount());
            cartItemRepository.save(cartItem);
        } else {
            cartItem.addCount(cartItemDto.getCount());
        }
        return cartItem.getId();
    }
}
