package org.koreait.models.cart;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.MemberUtil;
import org.koreait.commons.constants.CartStatus;
import org.koreait.entities.Cart;
import org.koreait.entities.QCart;
import org.koreait.models.member.MemberInfo;
import org.koreait.models.product.ProductInfoService;
import org.koreait.repositories.CartRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;

@Service
@RequiredArgsConstructor
public class CartInfoService {
    private final CartRepository cartRepository;
    private final MemberUtil memberUtil;
    private final ProductInfoService productInfoService;

    public List<Cart> gets(String mode) {
        mode = mode == null || mode.isBlank() ? "cart" : mode;

        QCart cart = QCart.cart;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(cart.mode.eq(mode));
        if (mode.equals("order")) {
            builder.and(cart.status.eq(CartStatus.ORDERING));
        }

        if (memberUtil.isLogin()) {
            MemberInfo member = memberUtil.getMember();
            builder.and(cart.createdBy.eq(member.getUserId())); // 회원별 장바구니 상품
        } else {
            builder.and(cart.createdBy.isNull()); // 비회원 장바구니 상태
            builder.and(cart.guestId.eq(memberUtil.getGuestId()));
        }

        List<Cart> items = (List<Cart>)cartRepository.findAll(builder, Sort.by(asc("createdAt")));

        items.forEach(item -> productInfoService.updateImageInfo(item.getProduct()));

        return items;
    }
}

