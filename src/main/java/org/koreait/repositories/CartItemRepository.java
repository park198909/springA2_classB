package org.koreait.repositories;

import org.koreait.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, QuerydslPredicateExecutor<CartItem> {

    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
