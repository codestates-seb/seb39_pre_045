package pre045.board_service.comment.QComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import pre045.board_service.member.entity.Member;
import pre045.board_service.question.entity.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class QCommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotNull
        private Long memberId;

        @NotNull
        private Question question;

        @NotBlank
        private String qCommentContent;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        @Positive
        private Long memberId;

        private Long commentId;


        @NotNull
        @Positive
        private Question question;

        @NotBlank
        private  String qCommentContent;

        public void setCommentId(Long commentId) {this.commentId = commentId;}

    }

    @Getter
    @AllArgsConstructor

    public static class Response {
        private String qCommentUsername;

        private String qCommentContent;

    }
}

