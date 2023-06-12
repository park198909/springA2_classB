package org.koreait.repositories;

import org.koreait.controllers.admins.products.ProductForm;
import org.koreait.entities.Product;
import org.koreait.entities.QProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository
        extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<ProductForm> {
    // 상품명으로 상품 조회
    Product findByProductNm(String productNm);

    // 아이디로 회원 존재 유무 체크
    default boolean exists (String productNm) {
        QProduct product = QProduct.product;

        return exists(product.productNm.eq(productNm));
    }
}
