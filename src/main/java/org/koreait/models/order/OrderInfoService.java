package org.koreait.models.order;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.koreait.controllers.admins.orders.OrderForm;
import org.koreait.controllers.admins.products.ProductForm;
import org.koreait.entities.Order;
import org.koreait.entities.Product;
import org.koreait.models.product.ProductNotFoundException;
import org.koreait.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInfoService {
    private final OrderRepository orderRepository;

    public Order get(Long orderNo) {
        Order order = orderRepository.findById(orderNo).orElseThrow(OrderNotFoundException::new);

        return order;
    }

    public OrderForm getOrderForm(Long orderNo) {
        Order order = get(orderNo);

        return new ModelMapper().map(order, OrderForm.class);
    }
}
