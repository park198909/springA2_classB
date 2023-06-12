package org.koreait.controllers.admins.products;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.product.ProductAddService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping
    public String index() {

        return "admin/product/index";
    }
}
