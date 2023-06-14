package org.koreait.models.product;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Product;
import org.koreait.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductListService {
    //230614 eunji
    private final ProductRepository productRepository;

    public List<Product> gets(){
        return productRepository.findAll(Sort.by(Sort.Order.desc("pNo")));
    }

}
