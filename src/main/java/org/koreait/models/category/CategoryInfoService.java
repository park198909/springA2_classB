package org.koreait.models.category;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Category;
import org.koreait.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryInfoService {
    private final CategoryRepository categoryRepository;

    public Category get(String cateCd) {
        Category category = categoryRepository.findById(cateCd).orElseThrow(NotRegisteredCateCdException::new);

        return category;
    }
}
