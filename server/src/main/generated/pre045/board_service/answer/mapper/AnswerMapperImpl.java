package pre045.board_service.answer.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre045.board_service.answer.dto.AnswerDto;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.AComment.AnswerComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T15:31:30+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostToAnswer(AnswerDto.Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setQuestion( postDto.getQuestion() );
        answer.setAnswerContent( postDto.getAnswerContent() );

        return answer;
    }

    @Override
    public Answer answerPatchToAnswer(AnswerDto.Patch patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setQuestion( patchDto.getQuestion() );
        answer.setAnswerId( patchDto.getAnswerId() );
        answer.setAnswerContent( patchDto.getAnswerContent() );

        return answer;
    }

    @Override
    public AnswerDto.Response answerToAnswerResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        List<AnswerComment> answerComments = null;
        Long answerId = null;
        String answerUsername = null;
        String answerContent = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        boolean adopted = false;
        int totalVotes = 0;

        List<AnswerComment> list = answer.getAnswerComments();
        if ( list != null ) {
            answerComments = new ArrayList<AnswerComment>( list );
        }
        answerId = answer.getAnswerId();
        answerUsername = answer.getAnswerUsername();
        answerContent = answer.getAnswerContent();
        createdAt = answer.getCreatedAt();
        modifiedAt = answer.getModifiedAt();
        adopted = answer.isAdopted();
        totalVotes = answer.getTotalVotes();

        AnswerDto.Response response = new AnswerDto.Response( answerId, answerUsername, answerContent, createdAt, modifiedAt, answerComments, adopted, totalVotes );

        return response;
    }
}
