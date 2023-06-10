package com.project.models.member;

import com.project.entities.Member;
import com.project.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

        MemberInfo memberInfo = new ModelMapper().map(member, MemberInfo.class);

        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(member.getType().toString()));

        memberInfo.setAuthorities(authorities);

        return memberInfo;
    }
}
