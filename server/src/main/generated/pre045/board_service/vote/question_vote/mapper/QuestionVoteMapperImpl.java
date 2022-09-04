package pre045.board_service.vote.question_vote.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre045.board_service.vote.question_vote.dto.QuestionVoteDto;
import pre045.board_service.vote.question_vote.entity.QuestionVote;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-04T00:32:36+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class QuestionVoteMapperImpl implements QuestionVoteMapper {

    @Override
    public QuestionVote questionVotePostToQuestionVote(QuestionVoteDto.Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        QuestionVote questionVote = new QuestionVote();

        return questionVote;
    }

    @Override
    public QuestionVoteDto.Response questionVoteToQuestionVoteResponse(QuestionVote questionVote) {
        if ( questionVote == null ) {
            return null;
        }

        int total = 0;

        total = questionVote.getTotal();

        QuestionVoteDto.Response response = new QuestionVoteDto.Response( total );

        return response;
    }
}
