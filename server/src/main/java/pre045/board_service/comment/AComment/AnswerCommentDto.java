package pre045.board_service.comment.AComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.answer.entity.Answer;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



public class AnswerCommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotNull
        private Answer answer;


        @NotBlank
        private String answerCommentContent;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        @Positive
        private Long memberId;

        private Long answerCommentId;


        @NotNull
        @Positive
        private Answer answer;

        @NotBlank
        private  String answerCommentContent;

        public void setAnswerCommentId(Long answerCommentId) {this.answerCommentId = answerCommentId;}

    }

    @Getter
    @AllArgsConstructor

    public static class Response {

        private Long answerCommentId;

        private String answerCommentUsername;

        private String answerCommentContent;

    }
}
