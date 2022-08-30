package pre045.board_service.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pre045.board_service.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
