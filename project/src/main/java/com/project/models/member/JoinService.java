package com.project.models.member;

import com.project.commons.constants.MemberType;
import com.project.controllers.member.JoinForm;
import com.project.entities.Member;
import com.project.models.member.social.ProfileResult;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final HttpSession session;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinForm joinForm) {
        Member member = new ModelMapper().map(joinForm, Member.class);
        member.setType(MemberType.USER);

        ProfileResult profileResult = (ProfileResult) session.getAttribute("kakao");
        if (profileResult == null) {
            String hash = passwordEncoder.encode(joinForm.getUserPw());
            member.setUserPw(hash);
        }else{
            String socialId = "" + profileResult.getId();
            String socialChannel = "kakao";
            member.setSocialId(socialId);
            member.setSocialChannel(socialChannel);
        }

    }
}
