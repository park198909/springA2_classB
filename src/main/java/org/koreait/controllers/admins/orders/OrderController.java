package org.koreait.controllers.admins.orders;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.commons.MenuDetail;
import org.koreait.commons.Menus;
import org.koreait.entities.Order;
import org.koreait.models.order.OrderConfigListService;
import org.koreait.models.order.OrderInfoService;
import org.koreait.models.order.OrderSaveService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrderController {

    private final HttpServletRequest request;
    private final OrderInfoService orderInfoService;
    private final OrderSaveService orderSaveService;
    private final OrderConfigListService orderConfigListService;

    @GetMapping
    public String index(@ModelAttribute OrderSearch orderSearch, Model model) {
        commonProcess(model,"주문목록");

        Page<Order> data = orderConfigListService.gets(orderSearch);
        model.addAttribute("items", data.getContent());

        return "admin/order/index";
    }

    @GetMapping("/add")
    public String register(@ModelAttribute OrderForm orderForm, Model model) {
        commonProcess(model, "주문수정");

        return "admin/order/register";
    }

    @GetMapping("/update/{orderNo}")
    public String update(@PathVariable Long orderNo, Model model) {
        commonProcess(model, "주문수정");

        OrderForm orderForm = orderInfoService.getOrderForm(orderNo);
        model.addAttribute("orderForm", orderForm);

        return "admin/order/update";
    }

    @PostMapping("/save")
    public String register(OrderForm orderForm, Errors errors, Model model) {
        Long orderNo = orderForm.getOrderNo();
        String tpl = "admin/order/update";

        commonProcess(model, "주문수정");

        if (errors.hasErrors()) {
            return "tpl";
        }

        try {
            // 주문 수정 처리
            orderSaveService.save(orderForm);
        } catch (CommonException e) {
            e.printStackTrace();

            errors.reject("orderSaveErr", e.getMessage());
        }


        return "redirect:/admin/order"; // 주문 수정 성공 -> 주문 목록
    }


    private void commonProcess(Model model, String title) {
        String URI = request.getRequestURI();

        // 서브 메뉴 처리
        String subMenuCode = Menus.getSubMenuCode(request);
        if (title.equals("주문수정")) subMenuCode="save";
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menus.gets("order");
        model.addAttribute("submenus", submenus);

        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);

        List<String> addScript = new ArrayList<>();
        if (subMenuCode.equals("save")) {
            addScript.add("fileManager");
            addScript.add("ckeditor/ckeditor");
            addScript.add("order/form");
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
