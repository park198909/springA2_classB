package org.koreait.models.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.entities.Category;
import org.koreait.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CategoryUpdateService {

    private final CategoryRepository categoryRepository;
    private final HttpServletRequest request;

    public void update() {
        String[] cateCds = request.getParameterValues("cateCd");
        if (cateCds == null || cateCds.length == 0) {
            throw new CommonException("수정할 분류를 선택하세요.", HttpStatus.BAD_REQUEST);
        }

        Arrays.stream(cateCds).forEach(this::updateProcess);

        categoryRepository.flush();

    }

    private void updateProcess(String cateCd) {
        Category category = categoryRepository.findById(cateCd).orElseThrow(NotRegisteredCateCdException::new);

        String use = request.getParameter("use_" + cateCd);
        String listOrder = request.getParameter("listOrder_" + cateCd);
        String cateNm = request.getParameter("cateNm_" + cateCd);

        category.setUse(Boolean.parseBoolean(use));
        category.setListOrder(Long.parseLong(listOrder));
        category.setCateNm(cateNm);

        categoryRepository.save(category);

    }
}
