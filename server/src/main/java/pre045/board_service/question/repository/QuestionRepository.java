package pre045.board_service.question.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pre045.board_service.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}
