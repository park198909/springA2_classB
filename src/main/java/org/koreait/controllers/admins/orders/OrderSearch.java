package org.koreait.controllers.admins.orders;

import lombok.Data;

/**
 * 상품 검색
 * 
 */
@Data
public class OrderSearch {
    private int page = 1;
    private int limit = 20;

    private String sopt; // 검색 조건
    private String skey; // 검색 키워드
}
