package org.koreait.controllers.admins.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.entities.Category;
import org.modelmapper.ModelMapper;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CategoryForm {
    private String cateCd; // 상품분류

    private int used;

    private String location;

    private String cateNm;

    private int listOrder;

    private String parentCateCd;

    public static Category of (CategoryForm categoryForm) {
        return new ModelMapper().map(categoryForm, Category.class);
    }}
