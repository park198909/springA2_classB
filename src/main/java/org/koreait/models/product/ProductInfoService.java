package org.koreait.models.product;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.ProductForm;
import org.koreait.entities.FileInfo;
import org.koreait.entities.Product;
import org.koreait.models.file.FileInfoService;
import org.koreait.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInfoService {

    private final ProductRepository productRepository;
    private final FileInfoService fileInfoService;

    public Product get(Long pNo) {
        Product product = productRepository.findById(pNo).orElseThrow(ProductNotFoundException::new);

        updateImageInfo(product);

        return product;
    }

    public ProductForm getFormData(Long pNo) {
        Product product = get(pNo);

        ProductForm productForm = new ModelMapper().map(product, ProductForm.class);

        if(productForm.getStock() == 0) {
            productForm.setStockType(0);
        } else{
            productForm.setStockType(1);
        }

        return productForm;
    }

    private void updateImageInfo(Product product) {
        String gid = product.getGid();

        // 에디터 이미지
        List<FileInfo> editorImages = fileInfoService.gets(gid, "editor", true);
        product.setEditorImages(editorImages);

        // 메인 이미지
        List<FileInfo> mainImages = fileInfoService.gets(gid, "main", true);
        product.setMainImage(mainImages != null && !mainImages.isEmpty() ? mainImages.get(0) : null);

        // 목록 이미지
        List<FileInfo> listImages = fileInfoService.gets(gid, "list", true);
        product.setListImage(listImages != null && !listImages.isEmpty() ? listImages.get(0) : null);
    }
}
