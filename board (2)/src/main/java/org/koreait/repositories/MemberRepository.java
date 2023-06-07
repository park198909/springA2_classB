package org.koreait.repositories;

import org.koreait.entities.Member;
import org.koreait.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Member findByUserId(String userId);

    default boolean exists(String userId) {
        QMember member = QMember.member;

        boolean result = exists(member.userId.eq(userId));

        return result;
    }

}
