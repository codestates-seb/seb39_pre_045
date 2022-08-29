package pre045.board_service.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.question.entity.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

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
        @Positive
        private Long memberId;

        private Question question;

        @NotBlank
        private String answerContent;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {
        @Positive
        private Long memberId;

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
//    @NoArgsConstructor
    public static class Response {
        private String answerUsername;

        private String answerContent;

        @NotNull
        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;
    }

}