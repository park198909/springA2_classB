package com.project.repositories;

import com.project.entities.Member;
import com.project.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Member findByUserId(String userId); // 아이디로 회원 조회

    default boolean exists(String userId) { // 아이디로 회원 존재 유무 체크

        QMember member = QMember.member;

        return exists(member.userId.eq(userId));

    }
}
