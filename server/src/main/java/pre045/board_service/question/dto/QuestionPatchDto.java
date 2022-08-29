package pre045.board_service.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class QuestionPatchDto {

    // Patch 요청으로 들어오는 값을 이렇게 받는다

    // NotBlank : null, "" , " " 모두 허용안함
    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "내용을 입력하세요")
    private String questionContent;



}
