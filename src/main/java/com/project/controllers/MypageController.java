package com.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@GetMapping("/info")
	public String info() {
		return "/mypage/info";
	}

	@GetMapping("/cart")
	public String cart() {
		return "/mypage/cart";
	}

	@GetMapping("/infoupdate")
	public String infoupdate() {
		return "/mypage/infoupdate";
	}
}
