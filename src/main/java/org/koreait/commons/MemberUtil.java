package org.koreait.commons;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.koreait.commons.constants.Role;
import org.koreait.entities.Member;
import org.koreait.models.member.MemberInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MemberUtil {

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    /**
     * 로그인 여부
     * @return
     */
    public boolean isLogin() {

        return getMember() != null;
    }

    /**
     * 관리자 여부
     * @return
     */
    public boolean isAdmin() {

        return isLogin() && getMember().getRoles() == Role.ADMIN;
    }

    public MemberInfo getMember() {

        MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");

        return memberInfo;
    }

    public Member getEntity() {

        if (isLogin()) {
            Member member = new ModelMapper().map(getMember(), Member.class);
            return member;
        }

        return null;
    }

    public int getGuestId() {
        /**
         * 비회원 - IP + User-Agent
         * 회원 - 회원 ID
         */
        if (isLogin()) { // 회원
            return Objects.hash(getMember().getUserId());
        } else { // 비회원
            String ip = request.getRemoteAddr();
            String ua = request.getHeader("User-Agent");

            return Objects.hash(ip, ua);
        }
    }
}
