package org.koreait.models.category;

import lombok.RequiredArgsConstructor;
import org.koreait.controllers.admins.products.CategorySearch;
import org.koreait.entities.Category;
import org.koreait.repositories.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.koreait.commons.Menus.gets;
import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class CategoryListService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return gets(null, 0, 0);
    }

    public List<Category> gets(CategorySearch search, int page, int limit) {

        Sort sort = Sort.by(desc("listOrder"), desc("createAt"));
        List<Category> categories = repository.findAll(sort);

        return categories;
    }
}