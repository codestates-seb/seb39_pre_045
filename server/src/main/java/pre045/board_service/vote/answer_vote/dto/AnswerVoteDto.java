package pre045.board_service.vote.answer_vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pre045.board_service.answer.entity.Answer;

import javax.validation.constraints.Positive;

public class AnswerVoteDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private Long memberId;

        @Positive
        private Answer answer;

    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private int total;
    }


}
