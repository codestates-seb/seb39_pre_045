package pre045.board_service.question.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.QComment.QComment;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionResponseDto {

    private Long questionId;

    private String title;

    private String questionContent;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private int view;

    private boolean checkAdopted;

    private String username;

    private int totalVotes;

    private List<Answer> answers;

    private List<QComment> qComments;


}
