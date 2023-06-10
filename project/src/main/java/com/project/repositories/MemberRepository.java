package com.project.repositories;

import com.project.entities.Member;
import com.project.entities.QMember;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Member findByUserId(String userId);

    Member findBySocialChannelAndSocialId(String channel, String id);

    default  boolean exists(String userId) {
        QMember member = QMember.member;

        boolean result = exists(member.userId.eq(userId));

        return result;
    }

    default boolean exists(String socialChannel, String socialId) {
        QMember member = QMember.member;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.socialChannel.eq(socialChannel))
                .and(member.socialId.eq(socialId));

        return exists(builder);
    }
}
