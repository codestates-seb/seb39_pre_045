package pre045.board_service.vote.answer_vote.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre045.board_service.vote.answer_vote.dto.AnswerVoteDto;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T15:31:30+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class AnswerVoteMapperImpl implements AnswerVoteMapper {

    @Override
    public AnswerVote answerVotePostToAnswerVote(AnswerVoteDto.Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        AnswerVote answerVote = new AnswerVote();

        answerVote.setAnswer( postDto.getAnswer() );

        return answerVote;
    }

    @Override
    public AnswerVoteDto.Response answerVoteToAnswerVoteResponse(AnswerVote answerVote) {
        if ( answerVote == null ) {
            return null;
        }

        int total = 0;

        total = answerVote.getTotal();

        AnswerVoteDto.Response response = new AnswerVoteDto.Response( total );

        return response;
    }
}
