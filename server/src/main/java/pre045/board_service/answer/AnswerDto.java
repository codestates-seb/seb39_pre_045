package pre045.board_service.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.member.Member;
import pre045.board_service.question.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * 보완사항
 * 1. Member 객체 자체를 그대로 받고 있음
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

    /**
     * 보완 사항
     * 1. Member
     *  -> 로그인 기능 구현 시 세션에서 username을 가져와 response로 client에 반환해주도록 수정할 것
     */
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
