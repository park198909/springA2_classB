package org.koreait.controllers.products;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.commons.Pagination;
import org.koreait.controllers.carts.CartForm;
import org.koreait.entities.Category;
import org.koreait.entities.Product;
import org.koreait.models.cart.CartSaveService;
import org.koreait.models.category.CategoryInfoService;
import org.koreait.models.product.ProductInfoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("frontProductController")
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductInfoService productInfoService;
    private final CategoryInfoService categoryInfoService;
    private final CartSaveService cartSaveService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    /** 상품 목록 */
    @GetMapping("/list/{cateCd}")
    public String list(@PathVariable String cateCd, @ModelAttribute ProductSearch search, Model model) {
        Category category = categoryInfoService.get(cateCd);
        commonProcess(model, category.getCateNm());
        search.setCateCd(cateCd);

        Page<Product> data = productInfoService.gets(search);
        Pagination pagination = new Pagination(data, request.getRequestURI());

        model.addAttribute("items", data.getContent());
        model.addAttribute("pagination", pagination);
        model.addAttribute("category", category);
        model.addAttribute("addCss", new String[] { "product/list"});

        return "product/list";
    }

    /** 상품 상세 보기 */
    @GetMapping("/view/{pNo}")
    public String view(@PathVariable Long pNo, Model model) {

        Product product = productInfoService.get(pNo);

        commonProcess(model, product.getPName());

        model.addAttribute("product", product);
        model.addAttribute("addCss", new String[] { "product/view"});
        model.addAttribute("addScript", new String[] { "product/form"} );
        return "product/view";
    }

    @PostMapping
    public String process(@ModelAttribute CartForm form, Model model) {
        String script = null;
        try {
            cartSaveService.save(form);
            String url = request.getContextPath();
            url += form.getMode().equals("order") ? "/order" : "/cart";
            script = String.format("parent.location.replace('%s');", url);
        } catch (CommonException e) {
            e.printStackTrace();
            script = String.format("Swal.fire('%s');", e.getMessage());
        }
        model.addAttribute("script", script);
        return "commons/sweetalert_script";
    }

    private void commonProcess(Model model, String title) {
        model.addAttribute("pageTitle", title);
    }

    @ExceptionHandler(CommonException.class)
    public String errorHandler(CommonException e, Model model) {
        e.printStackTrace();

        String message = e.getMessage();
        HttpStatus status = e.getStatus();
        response.setStatus(status.value());

        String script = String.format("Swal.fire('%s');history.back();", message);
        model.addAttribute("script", script);
        return "commons/sweetalert_script";
    }
}
