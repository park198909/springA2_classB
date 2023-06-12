package org.koreait.models.product;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.ProductForm;
import org.koreait.entities.Product;
import org.koreait.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAddService {

    private final ProductRepository productRepository;

    public void add(ProductForm productForm) {
        Product product = new ModelMapper().map(productForm, Product.class);
        productRepository.saveAndFlush(product);
    }

}
