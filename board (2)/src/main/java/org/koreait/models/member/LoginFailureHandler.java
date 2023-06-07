package org.koreait.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		HttpSession session = request.getSession();
		session.removeAttribute("loginField");
		session.removeAttribute("message");
		session.removeAttribute("userId");

		try {
			String userId = request.getParameter("userId");
			String userPw = request.getParameter("userPw");
			if (userId == null || userId.isBlank()) {
				throw new LoginValidationException("userId", "아이디를 입력하세요.");
			}

			session.setAttribute("userId", userId);

			if (userPw == null || userPw.isBlank()) {
				throw new LoginValidationException("userPw", "비밀번호를 입력하세요.");
			}


			throw new LoginValidationException("global", "아이디 또는 비밀번호가 일치하지 않습니다.");

		} catch (LoginValidationException e) {
			String field = e.getField();
			String message = e.getMessage();
			session.setAttribute("loginField", field);
			session.setAttribute("message", e.getMessage());
		}


		String url = request.getContextPath() + "/member/login";
		response.sendRedirect(url);
	}
}
