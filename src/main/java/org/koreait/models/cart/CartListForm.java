package org.koreait.models.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartListForm {
    private List<Long> id; // 장바구니 등록번호
    private String mode; // 처리 모드 - delete : 삭제, update - 수정, order - 주문
}
