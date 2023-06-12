package org.koreait.controllers.products;

import lombok.RequiredArgsConstructor;
import org.koreait.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ProductValidator implements Validator {

    private final ProductRepository productRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductForm productForm = (ProductForm) target;
        String productNm = productForm.getProductNm();

        /*
        * 상품명 중복 체크
        */
        if (productNm != null && !productNm.isBlank() && productRepository.exists(productNm)) {
            errors.rejectValue("productNm", "Validation.duplicate.productNm");
        }
    }
}
