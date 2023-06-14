package org.koreait.controllers.admins.products;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.commons.MenuDetail;
import org.koreait.commons.Menus;
import org.koreait.models.category.CategorySaveService;
import org.koreait.models.category.DuplicateCateCdException;
import org.koreait.models.product.ProductInfoService;
import org.koreait.models.product.ProductSaveService;
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

    @GetMapping
    public String index(Model model) {
        commonProcess(model, "상품목록");


        return "admin/product/index";
    }

    @GetMapping("/category")
    public String category(@ModelAttribute CategoryForm categoryForm, Model model) {
        commonProcess(model, "상품분류");


        return "admin/product/category";
    }

    @PostMapping("/categoryPs")
    public String categoryPs(@Valid CategoryForm categoryForm, Errors errors) {
        try {
            categorySaveService.save(categoryForm, errors);
        } catch (DuplicateCateCdException e) {
            errors.rejectValue("cateCd", "Duplicate.categoryForm.cateCd");
        }

        if (errors.hasErrors()) {
            return "admin/product/category";
        }

        return "redirect:/admin/product";
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
