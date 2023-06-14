package org.koreait.models.order;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.orders.OrderForm;
import org.koreait.entities.Order;
import org.koreait.entities.Product;
import org.koreait.models.product.ProductNotFoundException;
import org.koreait.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSaveService {

    private final OrderRepository orderRepository;

    public void save(OrderForm orderForm) {
        Long orderNo = orderForm.getOrderNo();
        Order order = orderRepository.findById(orderNo).orElseThrow(OrderNotFoundException::new);

        order.setOrderId(orderForm.getOrderId());
        order.setOrderStat(order.getOrderStat());
        order.setPrice(orderForm.getPrice());
        order.setOrderlist(orderForm.getOrderlist().toString());

        order = orderRepository.saveAndFlush(order);
        orderForm.setOrderNo(order.getOrderNo()); // 주문 번호
    }
}
