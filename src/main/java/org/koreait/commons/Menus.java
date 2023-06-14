package org.koreait.commons;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 서브 메뉴 조회
 *
 */
public class Menus {

    public static List<MenuDetail> gets(String code) {
        List<MenuDetail> menus = new ArrayList<>();

        // 게시판 하위 메뉴, 상품관리 하위 메뉴
        if (code.equals("board")) {
            menus.add(new MenuDetail("board", "게시판 목록", "/admin/board"));
            menus.add(new MenuDetail("register", "게시판 등록/수정", "/admin/board/register"));
            menus.add(new MenuDetail("posts", "게시글 관리", "/admin/board/posts"));
<<<<<<< HEAD:springboard/src/main/java/org/koreait/commons/Menus.java
        } else if (code.equals("product")) {
            menus.add(new MenuDetail("product", "상품 목록", "/admin/product"));
            menus.add(new MenuDetail("save", "상품 등록/수정", "/admin/product/add"));
=======
        } else if (code.equals("product")) { // 상품 관리
            menus.add(new MenuDetail("product","상품 목록","/admin/product"));
            menus.add(new MenuDetail("save", "상품 등록/수정", "/admin/product/add"));
            menus.add(new MenuDetail("category", "상품분류", "/admin/product/category"));
>>>>>>> 92709176c7bc92c76e67063536a852bee119c0ea:src/main/java/org/koreait/commons/Menus.java
        }

        return menus;
    }

    public static String getSubMenuCode(HttpServletRequest request) {
        String URI = request.getRequestURI();

        return URI.substring(URI.lastIndexOf('/') + 1);
    }
}
