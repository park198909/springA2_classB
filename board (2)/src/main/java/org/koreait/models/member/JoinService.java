package org.koreait.models.member;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.constants.MemberType;
import org.koreait.controllers.member.JoinForm;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinForm joinForm) {
        Member member = new ModelMapper().map(joinForm, Member.class);
        member.setType(MemberType.USER);

        String hash = passwordEncoder.encode(joinForm.getUserPw());
        member.setUserPw(hash);

        repository.saveAndFlush(member);
    }
}
