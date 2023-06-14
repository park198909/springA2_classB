package org.koreait.repositories;

import com.querydsl.core.BooleanBuilder;
import org.koreait.entities.Category;
import org.koreait.entities.QCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

public interface CategoryRepository extends JpaRepository<Category, String>, QuerydslPredicateExecutor {

    /**
     * 카테고리 등록 여부 체크
     *
     * @param cateCd
     * @return
     */
    default boolean exists(String cateCd) {
        QCategory category = QCategory.category;
        return exists(category.cateCd.eq(cateCd));
    }

    /**]
     * 노출 위치 코드별 카테고리 목록
     *
     * @param location
     * @param isAll - true : 전체 목록, false - 사용 중으로 표기된 카테고리 목록
     * @return
     */
    default List<Category> getCategories(String location, boolean isAll) {
        QCategory category = QCategory.category;
        BooleanBuilder builder = new BooleanBuilder();
        // 노출 위치별 조회
        if (location != null) {
            builder.and(category.location.eq(location));
        }

        if (!isAll) { // 사용중인 목록만 조회
            builder.and(category.use.eq(true));
        }

        List<Category> items = (List<Category>)this.findAll(builder, Sort.by(desc("listOrder")));

        return items;
    }

    default List<Category> getCategories(String location) {
        return getCategories(location,false);
    }
}