package org.koreait.models.product;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.ProductSearch;
import org.koreait.entities.Product;
import org.koreait.entities.QProduct;
import org.koreait.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class ProductConfigListService {
    //230614 eunji
    private final ProductRepository productRepository;

    public List<Product> gets(){
        return productRepository.findAll(Sort.by(Sort.Order.desc("pNo")));
    }

    public Page<Product> gets(ProductSearch productSearch){
        QProduct product = QProduct.product;

        BooleanBuilder andBuilder = new BooleanBuilder();

        int page = productSearch.getPage();
        int limit = productSearch.getLimit();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;

        /** 검색 조건 처리 S */
        String sopt = productSearch.getSopt();
        String skey = productSearch.getSkey();
        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            skey = skey.trim();
            sopt = sopt.trim();

            if (sopt.equals("all")) { // 통합 검색 - pName, cateCd
                BooleanBuilder orBuilder = new BooleanBuilder();
                orBuilder.or(product.pName.contains(skey))
                        .or(product.category.cateCd.contains(skey));
                andBuilder.and(orBuilder);

            } else if (sopt.equals("pName")) { // 게시판 아이디 bId
                andBuilder.and(product.pName.contains(skey));
            } else if (sopt.equals("cateCd")) { // 게시판명 bName
                andBuilder.and(product.category.cateCd.contains(skey));
            }
        }
        /** 검색 조건 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));
        Page<Product> data = productRepository.findAll(andBuilder, pageable);

        return data;

    }
}
