package com.project.models.member;

import com.project.controllers.members.JoinForm;
import com.project.entities.Member;
import com.project.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSaveService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public void save(JoinForm joinForm) {
        Member member = new ModelMapper().map(joinForm, Member.class);
        member.setUserPw(passwordEncoder.encode(joinForm.getUserPw()));

        memberRepository.saveAndFlush(member);

    }
}
