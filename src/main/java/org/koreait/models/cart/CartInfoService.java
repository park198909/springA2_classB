package org.koreait.models.cart;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Cart;
import org.koreait.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartInfoService {
    private final CartRepository cartRepository;

    public List<Cart> gets(String mode) {
        mode = mode == null || mode.isBlank() ? "cart" : mode;

        return null;
    }
}

