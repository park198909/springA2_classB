package org.koreait.controllers.products;

import jakarta.validation.Valid;
import org.modelmapper.internal.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/add")
    public String add(@ModelAttribute ProductForm productForm) {

        return "product/add";
    }

    @PostMapping("/add")
    public String addPs(@Valid ProductForm productForm, Errors errors, Model model) {

        return "redirect:/product/add";
    }
}
