package org.koreait.models.order;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.orders.OrderSearch;
import org.koreait.entities.Order;
import org.koreait.entities.QOrder;
import org.koreait.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Order.desc;

/**
 * 주문 조회 목록
 * 
 */
@Service
@RequiredArgsConstructor
public class OrderConfigListService {

    private final OrderRepository orderRepository;

    public Page<Order> gets(OrderSearch orderSearch) {
        QOrder order = QOrder.order;

        BooleanBuilder andBuilder = new BooleanBuilder();

        int page = orderSearch.getPage();
        int limit = orderSearch.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;

        /** 검색 조건 처리 S */
        String sopt = orderSearch.getSopt();
        String skey = orderSearch.getSkey();
        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            skey = skey.trim();
            sopt = sopt.trim();

            if (sopt.equals("all")) { // 통합 검색 -
                BooleanBuilder orBuilder = new BooleanBuilder();
                orBuilder.or(order.orderId.contains(skey))
                        .or(order.orderNm.contains(skey));
                andBuilder.and(orBuilder);

            } else if (sopt.equals("orderId")) { //
                andBuilder.and(order.orderId.contains(skey));
            } else if (sopt.equals("orderNm")) { //
                andBuilder.and(order.orderNm.contains(skey));
            }
        }
        /** 검색 조건 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));
        Page<Order> data = orderRepository.findAll(andBuilder, pageable);

        return data;
    }
}
