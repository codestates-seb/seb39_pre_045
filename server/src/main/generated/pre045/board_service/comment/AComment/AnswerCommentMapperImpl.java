package pre045.board_service.comment.AComment;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2022-09-05T18:22:15+0900",
=======
    date = "2022-09-05T18:25:03+0900",
>>>>>>> 60bb51d3137bb4c04f4c867123627a83fad0500c
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class AnswerCommentMapperImpl implements AnswerCommentMapper {

    @Override
    public AnswerComment answerCommentPostToComment(AnswerCommentDto.Post answerCommentPostDto) {
        if ( answerCommentPostDto == null ) {
            return null;
        }

        AnswerComment answerComment = new AnswerComment();

        answerComment.setAnswer( answerCommentPostDto.getAnswer() );
        answerComment.setAnswerCommentContent( answerCommentPostDto.getAnswerCommentContent() );

        return answerComment;
    }

    @Override
    public AnswerComment answerCommentPatchToComment(AnswerCommentDto.Patch answerCommentPatchDto) {
        if ( answerCommentPatchDto == null ) {
            return null;
        }

        AnswerComment answerComment = new AnswerComment();

        answerComment.setAnswer( answerCommentPatchDto.getAnswer() );
        answerComment.setAnswerCommentId( answerCommentPatchDto.getAnswerCommentId() );
        answerComment.setAnswerCommentContent( answerCommentPatchDto.getAnswerCommentContent() );

        return answerComment;
    }

    @Override
    public AnswerCommentDto.Response answerCommentToCommentResponse(AnswerComment answercomment) {
        if ( answercomment == null ) {
            return null;
        }

        Long answerCommentId = null;
        String answerCommentUsername = null;
        String answerCommentContent = null;

        answerCommentId = answercomment.getAnswerCommentId();
        answerCommentUsername = answercomment.getAnswerCommentUsername();
        answerCommentContent = answercomment.getAnswerCommentContent();

        AnswerCommentDto.Response response = new AnswerCommentDto.Response( answerCommentId, answerCommentUsername, answerCommentContent );

        return response;
    }
}
