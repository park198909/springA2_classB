package org.koreait.controllers.admins.products;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductForm {
    private Long pNo;
    private String gid = UUID.randomUUID().toString();

    private String cateCd; // 상품 분류

    @NotBlank
    private String pName; // 상품명

    private Long consumerPrice; // 소비자가
    private Long salePrice; // 판매가
    private int stockType; // 0 - 무제한, 1 - 특정 재고
    private Long stock; // 재고

    private String shortDescription; // 짧은 설명
    private String description; // 상세 설명
    
    
}
