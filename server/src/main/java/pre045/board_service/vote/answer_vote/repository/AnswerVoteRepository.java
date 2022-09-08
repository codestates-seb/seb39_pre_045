package pre045.board_service.vote.answer_vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
}
