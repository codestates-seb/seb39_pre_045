package pre045.board_service.answer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre045.board_service.answer.dto.AnswerDto;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.AComment.AnswerComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-05T18:22:16+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostToAnswer(AnswerDto.Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        Answer.AnswerBuilder answer = Answer.builder();

        answer.answerContent( postDto.getAnswerContent() );
        answer.question( postDto.getQuestion() );

        return answer.build();
    }

    @Override
    public Answer answerPatchToAnswer(AnswerDto.Patch patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        Answer.AnswerBuilder answer = Answer.builder();

        answer.answerId( patchDto.getAnswerId() );
        answer.answerContent( patchDto.getAnswerContent() );
        answer.question( patchDto.getQuestion() );

        return answer.build();
    }

    @Override
    public AnswerDto.Response answerToAnswerResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response.ResponseBuilder response = AnswerDto.Response.builder();

        response.answerId( answer.getAnswerId() );
        response.answerUsername( answer.getAnswerUsername() );
        response.answerContent( answer.getAnswerContent() );
        response.createdAt( answer.getCreatedAt() );
        response.modifiedAt( answer.getModifiedAt() );
        List<AnswerComment> list = answer.getAnswerComments();
        if ( list != null ) {
            response.answerComments( new ArrayList<AnswerComment>( list ) );
        }
        response.adopted( answer.isAdopted() );
        response.totalVotes( answer.getTotalVotes() );

        return response.build();
    }
}
