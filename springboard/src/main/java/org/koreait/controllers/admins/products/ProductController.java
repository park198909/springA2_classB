package org.koreait.controllers.admins.products;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.models.product.ProductAddService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductValidator productValidator;
    private final ProductAddService addService;

    @GetMapping("/add")
    public String add(@ModelAttribute ProductForm productForm) {

        return "product/add";
    }

    @PostMapping("/add")
    public String addPs(@Valid ProductForm productForm, Errors errors, Model model) {

        productValidator.validate(productForm, errors);

        if (errors.hasErrors()) {
            return "product/add";
        }

        addService.add(productForm);

        return "redirect:/product/add";
    }
}
