package org.koreait.models.product;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.ProductForm;
import org.koreait.controllers.admins.products.ProductSearch;
import org.koreait.entities.FileInfo;
import org.koreait.entities.Product;
import org.koreait.entities.QProduct;
import org.koreait.models.file.FileInfoService;
import org.koreait.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

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
        productForm.setCateCd(product.getCategory().getCateCd());

        if(productForm.getStock() == 0) {
            productForm.setStockType(0);
        } else{
            productForm.setStockType(1);
        }

        return productForm;
    }

    public void updateImageInfo(Product product) {
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

        /** 상품분류 검색 S */
        String cateCd = productSearch.getCateCd();
        if (cateCd != null && !cateCd.isBlank()) {
            andBuilder.and(product.category.cateCd.eq(cateCd));
        }
        /** 상품분류 검색 E */

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
        data.getContent().stream().forEach(this::updateImageInfo);

        return data;

    }
}
