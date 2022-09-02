package pre045.board_service.answer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.comment.AComment.AnswerComment;
import pre045.board_service.question.entity.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 보완사항
 * 1. 로그인 구현 후 Member 처리 방법 변경
 * 2. 최종 설계 완성 후 Entity의 Setter 제거 후 Dto에 Builder 설정 고려
 */
public class AnswerDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        private Question question;

        @NotBlank
        private String answerContent;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        private Question question;

        @Positive
        private Long answerId;

        @NotBlank
        private String answerContent;

        public void setAnswerId(Long answerId) {
            this.answerId = answerId;
        }
    }


    @Getter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        private Long answerId;

        private String answerUsername;

        private String answerContent;

        @NotNull
        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        private List<AnswerComment> answerComments;

        private boolean adopted;

        private int totalVotes;
    }

}
