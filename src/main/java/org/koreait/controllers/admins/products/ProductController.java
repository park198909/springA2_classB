package org.koreait.controllers.admins.products;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.MenuDetail;
import org.koreait.commons.Menus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class ProductController {

    private final HttpServletRequest request;

    @GetMapping
    public String index(Model model) {
        commonProcess(model, "상품목록");


        return "admin/product/index";
    }

    @GetMapping("/add")
    public String register(@ModelAttribute ProductForm productForm, Model model) {
        commonProcess(model, "상품등록");

        return "admin/product/register";
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

        // 상품 등록/수정 처리
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
            addScript.add("ckeditor/ckeditor");
            addScript.add("product/form");
        }

        model.addAttribute("addScript", addScript);

    }
}
