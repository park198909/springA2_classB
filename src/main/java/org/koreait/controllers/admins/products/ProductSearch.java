package org.koreait.controllers.admins.products;

import lombok.Data;

/**
 * 상품 검색
 * 
 */
@Data
public class ProductSearch {
    private int page = 1;
    private int limit = 20;

    private String sopt; //검색 조건
    private String skey; // 검색 키워드

    private String cateCd;
}
