package org.koreait.models.product;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.ProductForm;
import org.koreait.entities.Product;
import org.koreait.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInfoService {

    private final ProductRepository productRepository;
    public Product get(Long pNo){
        Product product =productRepository.findById(pNo).orElseThrow(ProductNotFoundException::new);

        return product;

    }

    public ProductForm getFormData(Long pNo){
        Product product = get(pNo);

        return new ModelMapper().map(product,ProductForm.class);
    }
}
