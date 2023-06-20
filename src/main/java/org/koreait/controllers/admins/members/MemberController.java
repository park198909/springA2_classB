package org.koreait.controllers.admins.members;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.koreait.commons.CommonException;
import org.koreait.commons.MenuDetail;
import org.koreait.commons.Menus;
import org.koreait.commons.Pagination;
import org.koreait.entities.Member;
import org.koreait.models.member.MemberInfoService;
import org.koreait.models.member.MemberSearch;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller("AdminMemberController")
@RequiredArgsConstructor
@RequestMapping("/admin/member")
public class MemberController {

    private final HttpServletRequest request;
    private final MemberInfoService infoService;

    @GetMapping
    public String index(@ModelAttribute MemberSearch memberSearch, Model model) {
        commonProcess(model, "회원목록");
        Page<Member> data = (Page<Member>) infoService.gets(memberSearch);
        model.addAttribute("items", data.getContent());
        data.getContent().stream().forEach(System.out::println);

        String url = request.getRequestURI();
        Pagination pagination = new Pagination(data, url);
        model.addAttribute("pagination", pagination);

        return "admin/member/index";
    }

    private void commonProcess(Model model, String title) {
        String URI = request.getRequestURI();

        // 서브 메뉴 처리
        String subMenuCode = Menus.getSubMenuCode(request);
        if (title.equals("주문수정")) subMenuCode="save";
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menus.gets("member");
        model.addAttribute("submenus", submenus);

        model.addAttribute("pageTitle", title);
        model.addAttribute("title", title);

        List<String> addScript = new ArrayList<>();
        if (subMenuCode.equals("save")) {
            addScript.add("fileManager");
            addScript.add("ckeditor/ckeditor");
            addScript.add("order/form");
        }

        model.addAttribute("addScript", addScript);

    }

    @ExceptionHandler(CommonException.class)
    public String errorHandler(CommonException e, HttpServletResponse response, Model model) {
        e.printStackTrace();

        response.setStatus(e.getStatus().value());
        String script = String.format("alert('%s');history.back();", e.getMessage());
        model.addAttribute("script", script);

        return "commons/execute_script";
    }
}
