package org.koreait.controllers.admins.products;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.commons.MenuDetail;
import org.koreait.commons.Menus;
import org.koreait.commons.Pagination;
import org.koreait.entities.Category;
import org.koreait.entities.Product;
import org.koreait.models.category.CategoryListService;
import org.koreait.models.category.CategorySaveService;
import org.koreait.models.category.CategoryUpdateService;
import org.koreait.models.category.DuplicateCateCdException;
import org.koreait.models.product.ProductInfoService;
import org.koreait.models.product.ProductSaveService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class ProductController {

    private final HttpServletRequest request;
    private final ProductSaveService productSaveService;
    private final ProductInfoService productInfoService;
    private final CategorySaveService categorySaveService;
    private final CategoryListService categoryListService;
    private final CategoryUpdateService categoryUpdateService;

    @GetMapping
    public String index(@ModelAttribute ProductSearch productSearch, Model model) {
        commonProcess(model, "상품목록");

        Page<Product> data = productInfoService.gets(productSearch);
        model.addAttribute("items", data.getContent());
        data.getContent().stream().forEach(System.out::println);

        String url = request.getRequestURI();
        Pagination pagination = new Pagination(data, url);
        model.addAttribute("pagination", pagination);

        return "admin/product/index";
    }

    @GetMapping("/category")
    public String category(Model model) {
        commonProcess(model, "상품분류");
        CategoryForm categoryForm = new CategoryForm();
        model.addAttribute("categoryForm", categoryForm);

        return "admin/product/category";

    }

    @PostMapping("/category")
    public String categoryPs(@Valid CategoryForm categoryForm, Errors errors, Model model) {
        commonProcess(model, "상품분류");
        try {
            categorySaveService.save(categoryForm, errors);
        } catch (DuplicateCateCdException e) {
            errors.rejectValue("cateCd", "Duplicate.categoryForm.cateCd");
        }

        if (errors.hasErrors()) {
            return "admin/product/category";
        }

        return "redirect:/admin/product/categories";
    }

    @GetMapping("/categories")
    public String categoryList(Model model) {
        commonProcess(model, "상품분류");

        List<Category> items = categoryListService.getAll();
        model.addAttribute("items", items);

        return "admin/product/category_list";
    }

    @PostMapping("/categories")
    public String categoryListPs(Model model) {
        String script = null;
        try {
            // 업데이트 처리
            categoryUpdateService.update();
        } catch (CommonException e) {
            e.printStackTrace();
            script = String.format("alert('%s');", e.getMessage());
        }

        // 업데이트 성공시
        script = String.format("alert('수정되었습니다.');parent.location.reload();");
        model.addAttribute("script", script);
        return "commons/execute_script";
    }


    @GetMapping("/add")
    public String register(@ModelAttribute ProductForm productForm, Model model) {
        commonProcess(model, "상품등록");

        return "admin/product/register";
    }

    @GetMapping("/update/{pNo}")
    public String update(@PathVariable Long pNo, Model model) {
        commonProcess(model, "상품수정");

        ProductForm productForm = productInfoService.getFormData(pNo);
        model.addAttribute("productForm", productForm);

        return "admin/product/update";
    }

    @PostMapping("/save")
    public String save(ProductForm productForm, Errors errors, Model model) {
        Long pNo = productForm.getPNo();
        String title = null;
        String tpl = "admin/product/";
        if (pNo == null) { // 상품 추가
            title = "상품등록";
            tpl += "register";
        } else {// 상품 수정
            title = "상품수정";
            tpl += "update";
        }

        commonProcess(model, title);

        if(productForm.getStock() == 0) {
            productForm.setStockType(0);
        } else{
            productForm.setStockType(1);
        }

        if (errors.hasErrors()) {
            return "tpl";
        }

        try {
            // 상품 등록/수정 처리
            productSaveService.save(productForm);
        } catch (CommonException e) {
            e.printStackTrace();

            errors.reject("productSaveErr", e.getMessage());
        }


        return "redirect:/admin/product"; // 상품 등록/수정 성공 -> 상품 목록
    }

    private void commonProcess(Model model, String title) {
        String URI = request.getRequestURI();


        // 서브 메뉴 처리
        String subMenuCode = Menus.getSubMenuCode(request);
        if (title.equals("상품등록") || title.equals("상품수정")) subMenuCode="save";
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menus.gets("product");
        model.addAttribute("submenus", submenus);

        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);

        List<String> addScript = new ArrayList<>();
        if (subMenuCode.equals("save")) {
            addScript.add("fileManager");
            addScript.add("ckeditor/ckeditor");
            addScript.add("product/form");

            List<Category> categories = categoryListService.getAll();
            model.addAttribute("categories", categories);
        }

        model.addAttribute("addScript", addScript);

    }

    @ExceptionHandler(CommonException.class)
    public String errorHandler(CommonException e, HttpServletResponse response, Model model) {
        e.printStackTrace();

        response.setStatus(e.getStatus().value());
        String script = String.format("alert('%s');history.back();", e.getMessage());
        model.addAttribute("script", script);

        return "commons/execute_script";
    }
}
