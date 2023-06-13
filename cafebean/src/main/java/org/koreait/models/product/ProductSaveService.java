package org.koreait.models.product;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.ProductForm;
import org.koreait.entities.Product;
import org.koreait.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSaveService {
    private final ProductRepository productRepository;

    /**
     * 상품 정보 저장
     *
     * @param productForm
     *          - pNo 있으면 수정, 없으면 추가
     *          - pNo 있고, 조회시 없으면 ProductNotFoundException
     */
    public void save(ProductForm productForm) {
        Long pNo = productForm.getPNo();

        Product product = null;
        if (pNo != null) { // 수정
            product = productRepository.findById(pNo).orElseThrow(ProductNotFoundException::new);

            product.setCateCd(productForm.getCateCd());
            product.setPName(productForm.getPName());
            product.setStock(productForm.getStock());
            product.setConsumerPrice(productForm.getConsumerPrice());
            product.setSalePrice(productForm.getSalePrice());
            product.setShortDescription(productForm.getShortDescription());
            product.setDescription(productForm.getDescription());
        } else { // 추가
            product = new ModelMapper().map(productForm, Product.class);
        }

        product = productRepository.saveAndFlush(product);
        productForm.setPNo(product.getPNo()); // 상품 등록 번호
    }
}