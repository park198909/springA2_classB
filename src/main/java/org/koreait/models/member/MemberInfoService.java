package org.koreait.models.member;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.entities.QMember;
import org.koreait.repositories.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repository.findByUserId(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities
                = Arrays.asList(new SimpleGrantedAuthority(member.getRoles().toString()));

        return MemberInfo.builder()
                .userNo(member.getUserNo())
                .userId(member.getUserId())
                .userPw(member.getUserPw())
                .userNm(member.getUserNm())
                .email(member.getEmail())
                .mobile(member.getMobile())
                .roles(member.getRoles())
                .authorities(authorities)
                .build();

    }

    public List<Member> gets(){
        return repository.findAll(Sort.by(Sort.Order.desc("userNo")));
    }

    public Page<Member> gets(MemberSearch memberSearch){
        QMember member = QMember.member;

        BooleanBuilder andBuilder = new BooleanBuilder();

        int page = memberSearch.getPage();
        int limit = memberSearch.getNoOfRow();
        page = page < 1 ? 1 : page;
        limit = limit < 1 ? 20 : limit;

        /** 검색 조건 처리 S */
        String sopt = memberSearch.getSopt();
        String skey = memberSearch.getSkey();
        if (sopt != null && !sopt.isBlank() && skey != null && !skey.isBlank()) {
            skey = skey.trim();
            sopt = sopt.trim();

            if (sopt.equals("all")) { // 통합 검색 - pName, cateCd
                BooleanBuilder orBuilder = new BooleanBuilder();
                orBuilder.or(member.userNm.contains(skey))
                        .or(member.userNm.contains(skey));
                andBuilder.and(orBuilder);

            } else if (sopt.equals("userId")) { // 게시판 아이디 bId
                andBuilder.and(member.userId.contains(skey));
            } else if (sopt.equals("userNm")) { // 게시판명 bName
                andBuilder.and(member.userNm.contains(skey));
            }
        }
        /** 검색 조건 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));
        Page<Member> data = repository.findAll(andBuilder, pageable);

        return data;

    }

}
