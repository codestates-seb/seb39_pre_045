package pre045.board_service.comment.AComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.member.entity.Member;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



public class ACommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotNull
        private Answer answer;

        @NotNull
        private Long memberId;

        @NotBlank
        private String aCommentContent;

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
        private Answer answer;

        @NotBlank
        private  String aCommentContent;

        public void setCommentId(Long commentId) {this.commentId = commentId;}

    }

    @Getter
    @AllArgsConstructor

    public static class Response {

        private String aCommentUsername;

        private String aCommentContent;

    }
}
