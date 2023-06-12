package org.koreait.controllers.products;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductForm {

    @NotBlank
    @Size(min=3, max=10)
    private String productNm; // 품명

    @NotBlank
    private Integer price; // 가격

    @Size(max=100)
    private String productDetail; // 상세설명
}
