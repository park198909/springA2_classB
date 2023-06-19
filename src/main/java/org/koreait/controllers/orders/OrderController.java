package org.koreait.controllers.orders;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("frontOrderController")
@RequestMapping("/order")
public class OrderController {
    @GetMapping
    public String index() {

        return "order/index";
    }
}
