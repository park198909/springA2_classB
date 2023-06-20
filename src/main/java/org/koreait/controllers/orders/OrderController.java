package org.koreait.controllers.orders;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Cart;
import org.koreait.models.cart.CartInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("frontOrderController")
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final CartInfoService cartInfoService;

    @GetMapping
    public String index(Model model) {
        commonProcess(model, "주문하기");

        return "order/index";
    }

    @PostMapping
    public String process(Model model) {
        commonProcess(model, "주문하기");
        return "redirect:/order/{id}"; // /order/주문번호 - 주문서 보기 페이지
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        commonProcess(model, "주문완료");
        return "order/view";
    }

    private void commonProcess(Model model, String title) {
        List<Cart> items = cartInfoService.gets("order");
        model.addAttribute("items", items);
        model.addAttribute("addCss", new String[] {"cart/style"});
        model.addAttribute("mode", "order");
        model.addAttribute("pageTitle", title);
    }
}
