package org.koreait.models.cart;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.MemberUtil;
import org.koreait.commons.constants.CartStatus;
import org.koreait.entities.Cart;
import org.koreait.entities.QCart;
import org.koreait.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartUpdateService {
    private final CartRepository cartRepository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;

    public void update(CartListForm form) {
        QCart cart = QCart.cart;
        String mode = form.getMode();

        // 소유하고 있는 장바구니 상품 전체 상태 READY -> 장바구니 id에 해당하는 상품만 ORDERING 변경
        updateReady();

        List<Cart> items = (List<Cart>)cartRepository.findAll(cart.id.in(form.getId()));

        for (Cart item : items) {
            String _cnt = request.getParameter("productCnt_" + item.getId());
            if (_cnt == null) continue;

            long cnt = Long.parseLong(_cnt);
            item.setProductCnt(cnt);

            if (mode.equals("order")) {
                item.setMode("order");
                item.setStatus(CartStatus.ORDERING);
            }
        }

        cartRepository.saveAllAndFlush(items);
    }

    private void updateReady() { // order -> cart, status = READY
        QCart cart = QCart.cart;
        BooleanBuilder builder = new BooleanBuilder();
        if (memberUtil.isLogin()) { // 회원일 경우
            builder.and(cart.createdBy.eq(memberUtil.getMember().getUserId()));
        } else { // 비회원일 경우
            builder.and(cart.createdBy.isNull()).and(cart.guestId.eq(memberUtil.getGuestId()));
        }

        List<Cart> items = (List<Cart>) cartRepository.findAll(builder);
        for (Cart item : items) {
            item.setMode("cart");
            item.setStatus(CartStatus.READY);
        }
    }
}
