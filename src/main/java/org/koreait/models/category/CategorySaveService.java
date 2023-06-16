package org.koreait.models.category;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.CategoryForm;
import org.koreait.entities.Category;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class CategorySaveService {
    private final CategoryRepository repository;
    private final CategorySaveValidator validator;

    public void save(CategoryForm categoryForm, Errors errors) {
        if (errors != null && errors.hasErrors()) {
            return;
        }

        validator.check(categoryForm, errors);

        /** 엔티티가 이미 등록된 분류라면 기존 엔티티를 가져오고
         *  없다면 새로운 엔티티로 변환한다 CategoryForm.of(categoryForm);
         * */

        String cateCd = categoryForm.getCateCd();
        Category category = null;
        if (cateCd != null && repository.exists(cateCd)) { // 이미 등록 되있을때
            category = repository.findById(cateCd).orElseGet(() -> CategoryForm.of(categoryForm));
            category.setCateCd(cateCd);
            category.setCateNm(categoryForm.getCateNm());
        }

        if (category == null) {
            // CategoryForm -> Category Entity 변환
            category = CategoryForm.of(categoryForm);
        }

        // 엔티티 저장, 수정
        repository.saveAndFlush(category);
    }
}
