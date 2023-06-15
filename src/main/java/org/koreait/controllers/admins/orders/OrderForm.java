package org.koreait.controllers.admins.orders;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderForm {
    private Long orderNo;

    private String gid = UUID.randomUUID().toString();

    private String[] orderlist; // 주문품목
    @NotBlank
    private String orderId; // 주문자Id
    private String orderNm; // 주문자명
    @NotBlank
    private String orderStat; // 주문 진행 상태
    @NotBlank
    private long price; // 주문가격
}
