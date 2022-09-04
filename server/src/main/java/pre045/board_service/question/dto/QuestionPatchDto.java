package pre045.board_service.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionPatchDto {
    @Positive
    private long questionId;

    // NotBlank : null, "" , " " 모두 허용안함
    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "내용을 입력하세요")
    private String questionContent;

    public void setQuestionId(long questionId){
        this.questionId = questionId;
    }


}
