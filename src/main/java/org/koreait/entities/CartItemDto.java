package org.koreait.entities;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartItemDto {

    private Long id; // 상품 코드

    @Min(value = 1)
    private int count;
}
