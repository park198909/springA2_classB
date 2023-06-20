package org.koreait.models.category;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Category;
import org.koreait.entities.QCategory;
import org.koreait.repositories.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class CategoryListService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return gets(true);
    }

    public List<Category> gets(boolean isAll) {
        BooleanBuilder builder = new BooleanBuilder();
        QCategory category = QCategory.category;
        if (!isAll) {
            builder.and(category.use.eq(true));
        }
        List<Category> items = (List<Category>)repository.findAll(builder,
                Sort.by(desc("listOrder"), desc("createdAt")));

        return items;
    }
}
