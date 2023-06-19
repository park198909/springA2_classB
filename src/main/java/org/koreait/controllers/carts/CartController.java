package org.koreait.controllers.carts;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.entities.Cart;
import org.koreait.models.cart.CartDeleteService;
import org.koreait.models.cart.CartInfoService;
import org.koreait.models.cart.CartListForm;
import org.koreait.models.cart.CartUpdateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartInfoService infoService;
    private final CartDeleteService deleteService;
    private final CartUpdateService updateService;

    @GetMapping
    public String index(Model model) {
        List<Cart> items = infoService.gets("cart");
        model.addAttribute("items", items);
        model.addAttribute("addCss", new String[] {"cart/style"});
        model.addAttribute("addScript", new String[] {"cart/form"});
        model.addAttribute("pageTitle", "장바구니");

        return "cart/index";
    }

    @PostMapping
    public String process(CartListForm form, Model model) {

        String script = "parent.location.reload();";
        String mode = form.getMode();
        if (mode.equals("delete")) { // 삭제
            deleteService.delete(form);
        } else if (mode.equals("update")) { // 수정
            updateService.update(form);
        } else { // 주문
            updateService.update(form);
            script = "parent.location.replace('/order');";
        }

        model.addAttribute("script", script);
        return "commons/sweetalert_script";
    }

   @ExceptionHandler(CommonException.class)
   public String errorHandler(CommonException e, Model model, HttpServletResponse response) {

        response.setStatus(e.getStatus().value());

        String script = String.format("Swal.fire('%s');", e.getMessage());
        model.addAttribute("script", script);
        return "commons/sweetalert_script";
   }
}
