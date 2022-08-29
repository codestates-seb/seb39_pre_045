package pre045.board_service.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.member.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



public class CommentDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        @NotNull
        private Answer answer;

        @NotBlank
        private String commentContent;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        private Long commentId;

        @NotNull
        @Positive
        private Member member;

        @NotNull
        @Positive
        private Answer answer;

        @NotBlank
        private  String commentContent;

        public void setCommentId(Long commentId) {this.commentId = commentId;}

    }

    @Getter
    @AllArgsConstructor

    public static class Response {
        private String commentUsername;

        private String commentContent;

    }



}
