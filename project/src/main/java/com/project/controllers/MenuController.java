package com.project.controllers;

import com.project.entities.BoardData;
import com.project.entities.Member;
import com.project.entities.Product;
import com.project.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final ProductRepository productRepository;

    private void insertData(){
        Product product = Product.builder()
                .gid()
                .cateCd("Coffee")
                .pName("아이스 아메리카노")
                .consumerPrice(5000)
                .salePrice(5000)
                .stock(9999)
                .shortDescription("시원한 아이스 아메리카노")
                .description("고소한 에스프레소가 어우러진 시원한 아메리카노")
                .build();

        product = productRepository.saveAndFlush(product);

        List<BoardData> items = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            Product item = Product.builder()
                    .gid()
                    .cateCd()
                    .pName()
                    .consumerPrice()
                    .salePrice()
                    .stock()
                    .shortDescription()
                    .description()
                    .build();
        }
        productRepository.saveAllAndFlush(items);
    }

    @GetMapping("/menu_list")
    public String menuList(){
        insertData();
        return "menu/menu_list";
    }

    @GetMapping("/menu_view")
    public String menuView(){
        return "menu/menu_view";
    }
}
