package pre045.board_service.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pre045.board_service.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
