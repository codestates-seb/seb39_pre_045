package pre045.board_service.vote.answer_vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerVoteResponseDto {

    private int total;

    public static AnswerVoteResponseDto of(AnswerVote answerVote) {
        return AnswerVoteResponseDto.builder()
                .total(answerVote.getTotal())
                .build();
    }


}
