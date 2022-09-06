package pre045.board_service.comment.QComment;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2022-09-05T18:22:16+0900",
=======
    date = "2022-09-05T18:25:03+0900",
>>>>>>> 60bb51d3137bb4c04f4c867123627a83fad0500c
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class QuestionCommentMapperImpl implements QuestionCommentMapper {

    @Override
    public QuestionComment questionCommentPostToComment(QuestionCommentDto.Post questionCommentPostDto) {
        if ( questionCommentPostDto == null ) {
            return null;
        }

        QuestionComment questionComment = new QuestionComment();

        questionComment.setQuestion( questionCommentPostDto.getQuestion() );
        questionComment.setQuestionCommentContent( questionCommentPostDto.getQuestionCommentContent() );

        return questionComment;
    }

    @Override
    public QuestionComment questionCommentPatchToComment(QuestionCommentDto.Patch questionCommentPatchDto) {
        if ( questionCommentPatchDto == null ) {
            return null;
        }

        QuestionComment questionComment = new QuestionComment();

        questionComment.setQuestion( questionCommentPatchDto.getQuestion() );
        questionComment.setQuestionCommentId( questionCommentPatchDto.getQuestionCommentId() );
        questionComment.setQuestionCommentContent( questionCommentPatchDto.getQuestionCommentContent() );

        return questionComment;
    }

    @Override
    public QuestionCommentDto.Response questionCommentToCommentResponse(QuestionComment questioncomment) {
        if ( questioncomment == null ) {
            return null;
        }

        Long questionCommentId = null;
        String questionCommentUsername = null;
        String questionCommentContent = null;

        questionCommentId = questioncomment.getQuestionCommentId();
        questionCommentUsername = questioncomment.getQuestionCommentUsername();
        questionCommentContent = questioncomment.getQuestionCommentContent();

        QuestionCommentDto.Response response = new QuestionCommentDto.Response( questionCommentId, questionCommentUsername, questionCommentContent );

        return response;
    }
}
