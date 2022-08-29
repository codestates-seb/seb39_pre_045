package pre045.board_service.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pre045.board_service.member.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QuestionPostDto {

    // Post 요청으로 들어오는 값을 이렇게 받는다

    @Positive
    private long memberId;

    @NotBlank
    private String title;

    @NotBlank
    private String questionContent;


}
