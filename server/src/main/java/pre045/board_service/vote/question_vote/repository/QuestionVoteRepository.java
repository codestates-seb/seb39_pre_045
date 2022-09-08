package pre045.board_service.vote.question_vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pre045.board_service.vote.question_vote.entity.QuestionVote;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
}
