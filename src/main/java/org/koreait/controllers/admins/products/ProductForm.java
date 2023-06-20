package org.koreait.controllers.admins.products;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.entities.FileInfo;

import java.util.List;
import java.util.UUID;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductForm {
    private Long pNo; // 상품번호
    private String gid = UUID.randomUUID().toString();

    private String cateCd; // 상품분류

    @NotBlank
    private String pName; // 상품명

    private long consumerPrice; // 소비자가
    private long salePrice; // 판매가
    private int stockType; // 0 - 무제한, 1 - 특정재고
    private long stock; // 재고


    private String shortDescription; // 짧은 설명
    private String description; // 상세 설명

    private FileInfo mainImage; // 메인 이미지

    private FileInfo listImage; // 목록 이미지

    private List<FileInfo> editorImages; // 에디터 이미지

}
