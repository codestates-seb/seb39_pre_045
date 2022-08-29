package pre045.board_service.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class QuestionResponseDto {

    private long questionId;

    private String title;

    private String questionContent;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    private int view;



}
