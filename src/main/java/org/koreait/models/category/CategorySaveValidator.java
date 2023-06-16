package org.koreait.models.category;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.commons.validators.RequiredValidator;
import org.koreait.commons.validators.ServiceValidator;
import org.koreait.controllers.admins.products.CategoryForm;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategorySaveValidator implements ServiceValidator<CategoryForm>, RequiredValidator {
    private final CategoryRepository repository;

    @Override
    public void check(CategoryForm categoryForm) {
        nullCheck(categoryForm, new CommonException("잘못된 접근입니다."));

        requiredCheck(categoryForm.getCateCd(), new CommonException("분류코드를 입력하세요."));
        requiredCheck(categoryForm.getCateNm(), new CommonException("분류명을 입력하세요."));

        if (repository.exists(categoryForm.getCateCd())) {
            throw new DuplicateCateCdException();
        }
    }
}
