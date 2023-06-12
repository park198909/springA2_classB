package org.koreait.controllers.admins.products;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.MenuDetail;
import org.koreait.commons.Menus;
import org.koreait.models.product.ProductAddService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    private final HttpServletRequest request;

    @GetMapping
    public String index(Model model) {
        commonProcess(model, "상품목록");


        return "admin/product/index";
    }

    @GetMapping("/add")
    public String register(Model model) {
        commonProcess(model, "상품등록");

        return "admin/product/register";
    }

    private void commonProcess(Model model, String title) {
        String URI = request.getRequestURI();

        // 서브 메뉴 처리
        String subMenuCode = Menus.getSubMenuCode(request);
        if (title.equals("상품등록") ||  title.equals("상품수정")) subMenuCode = "save";

        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menus.gets("product");
        model.addAttribute("submenus", submenus);

        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);

        List<String> addScript = new ArrayList<>();
        if (subMenuCode.equals("save")) {
            addScript.add("admin/ckeditor/ckeditor");
            addScript.add("admin/product/form");
        }

        model.addAttribute("addScript", addScript);
    }
}
