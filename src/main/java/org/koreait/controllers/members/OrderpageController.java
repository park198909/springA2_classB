package org.koreait.controllers.members;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/order")
public class OrderpageController {

    @GetMapping("/delivery")
    public String delivery() {
        return "/order/delivery";
    }

    @GetMapping("/pickup")
    public String pickup() {
        return "/order/pickup";
    }

    @GetMapping("/success")
    public String success() {
        return "/order/success";
    }
}