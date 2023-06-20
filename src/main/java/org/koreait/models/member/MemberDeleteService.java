package org.koreait.models.member;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.Member;
import org.koreait.models.board.BoardDataNotExistsException;
import org.koreait.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberDeleteService {
    private final MemberRepository repository;
    /**
     * 회원 탈퇴
     *
     * @param id 회원 번호
     * @param isComplete : false - 소프트 삭제, true - 완전 삭제
     */
    public void delete(Long id, boolean isComplete) {
        Member Member = repository.findById(id).orElseThrow(BoardDataNotExistsException::new);

        if (isComplete) { // 완전 삭제
            repository.delete(Member);
        } else { // 소프트 삭제
            Member.setDeletedAt(LocalDateTime.now());
        }

        repository.flush();
    }

    public void delete(Long id) {
        delete(id, false);
    }
}
