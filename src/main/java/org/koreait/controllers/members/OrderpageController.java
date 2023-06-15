package org.koreait.controllers.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/order")
public class OrderpageController {

    @GetMapping("/delivery")
    public String infoupdate(@ModelAttribute JoinForm joinForm, Model model) {
        commonProcess(model);
        return "/order/delivery";
    }
//
//    @GetMapping("/pickup")
//    public String pickup() {
//        return "/order/pickup";
//    }

    @GetMapping("/success")
    public String success() {
        return "/order/success";
    }

    private void commonProcess(Model model) {
        model.addAttribute("pageTitle", "결제하기");
    }
}