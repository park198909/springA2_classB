package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/menu_list")
    public String menuList(){
        return "menu/menu_list";
    }

    @GetMapping("/menu_view")
    public String menuView(){
        return "menu/menu_view";
    }
}
