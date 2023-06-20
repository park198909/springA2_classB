package org.koreait.models.member;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSearchService {
    private final MemberRepository repository;

    public String idSearch(String userNm, String mobile) {

        Member member = repository.findByUserNmAndMobile(userNm, mobile);

        String userId = member.getUserId();

        return userId;
    }
}