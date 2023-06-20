package org.koreait.models.cart;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Cart;
import org.koreait.entities.QCart;
import org.koreait.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDeleteService {
    private final CartRepository cartRepository;

    public void delete(CartListForm form) {
        QCart cart = QCart.cart;
        List<Cart> items = (List<Cart>)cartRepository.findAll(cart.id.in(form.getId()));
        items.stream().forEach(i -> cartRepository.delete(i));

        cartRepository.flush();
    }

}
