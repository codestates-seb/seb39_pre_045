package pre045.board_service.answer;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-27T13:23:29+0900",
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

        answer.setAnswerContent( postDto.getAnswerContent() );
        answer.setQuestion( postDto.getQuestion() );

        return answer;
    }

    @Override
    public Answer answerPatchToAnswer(AnswerDto.Patch patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( patchDto.getAnswerId() );
        answer.setAnswerContent( patchDto.getAnswerContent() );
        answer.setMember( patchDto.getMember() );
        answer.setQuestion( patchDto.getQuestion() );

        return answer;
    }

    @Override
    public AnswerDto.Response answerToAnswerResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        String answerUsername = null;
        String answerContent = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;

        answerUsername = answer.getAnswerUsername();
        answerContent = answer.getAnswerContent();
        createdAt = answer.getCreatedAt();
        modifiedAt = answer.getModifiedAt();

        AnswerDto.Response response = new AnswerDto.Response( answerUsername, answerContent, createdAt, modifiedAt );

        return response;
    }
}
