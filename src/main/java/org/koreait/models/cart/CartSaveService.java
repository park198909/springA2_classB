package org.koreait.models.cart;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.carts.CartForm;
import org.koreait.entities.Cart;
import org.koreait.entities.Product;
import org.koreait.models.product.ProductNotFoundException;
import org.koreait.repositories.CartRepository;
import org.koreait.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartSaveService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public void save(CartForm form) {
        Long id = form.getId();
        Long pNo = form.getPNo();
        Product product = productRepository.findById(pNo).orElseThrow(ProductNotFoundException::new);

        Cart cart = null;
        if (id != null) {
            cart = cartRepository.findById(id).orElseGet(Cart::new);
        }

        cart.setMode(form.getMode());
        cart.setProduct(product);
        cart.setProductCnt(form.getProductCnt());

        cart = cartRepository.saveAndFlush(cart);

        form.setId(cart.getId());
    }

}