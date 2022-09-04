package pre045.board_service.vote.question_vote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.vote.question_vote.entity.QuestionVote;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionVoteResponseDto {

    private int total;

    public static QuestionVoteResponseDto of(QuestionVote questionVote) {
        return QuestionVoteResponseDto.builder()
                .total(questionVote.getTotal())
                .build();
    }

}


