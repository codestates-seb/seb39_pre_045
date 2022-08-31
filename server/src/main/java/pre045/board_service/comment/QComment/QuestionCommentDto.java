package pre045.board_service.comment.QComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.answer.entity.Answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class QuestionCommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotNull
        private Answer answer;

        @NotNull
        private Long memberId;

        @NotBlank
        private String questionCommentContent;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        @Positive
        private Long memberId;

        private Long questionCommentId;


        @NotNull
        @Positive
        private Answer answer;

        @NotBlank
        private  String questionCommentContent;

        public void setQuestionCommentId(Long questionCommentId) {this.questionCommentId = questionCommentId;}

    }

    @Getter
    @AllArgsConstructor

    public static class Response {

        private Long questionCommentId;

        private String questionCommentUsername;

        private String questionCommentContent;

    }
}