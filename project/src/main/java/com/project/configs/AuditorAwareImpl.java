package com.project.configs;

import com.project.commons.MemberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<Long> {

    private final MemberUtils memberUtils;

    @Override
    public Optional<Long> getCurrentAuditor() {

        Long userNo = memberUtils.isLogin() ? memberUtils.getMember().getUserNo() : null;


        return Optional.ofNullable(userNo);
    }
}
