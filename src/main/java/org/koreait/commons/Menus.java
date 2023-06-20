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

        // 게시판 하위 메뉴
        if (code.equals("board")) {
            menus.add(new MenuDetail("board", "게시판 목록", "/admin/board"));
            menus.add(new MenuDetail("register", "게시판 등록/수정", "/admin/board/register"));
            menus.add(new MenuDetail("posts", "게시글 관리", "/admin/board/posts"));
        } else if (code.equals("product")) { // 상품 관리
            menus.add(new MenuDetail("product","상품 목록","/admin/product"));
            menus.add(new MenuDetail("save", "상품 등록/수정", "/admin/product/add"));
            menus.add(new MenuDetail("categories", "상품분류 목록", "/admin/product/categories"));
            menus.add(new MenuDetail("category", "상품분류 등록", "/admin/product/category"));
        } else if (code.equals("order")) { // 주문 관리
            menus.add(new MenuDetail("order", "주문 목록", "/admin/order"));
            menus.add(new MenuDetail("save", "주문 수정", "/admin/order/add"));
        } else if (code.equals("member")) { // 회원 관리
            menus.add(new MenuDetail("member", "회원 목록", "/admin/member"));
        }

        return menus;
    }

    public static String getSubMenuCode(HttpServletRequest request) {
        String URI = request.getRequestURI();

        return URI.substring(URI.lastIndexOf('/') + 1);
    }
}
