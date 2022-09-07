package pre045.board_service.question.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.QComment.QuestionComment;
import pre045.board_service.question.dto.QuestionPatchDto;
import pre045.board_service.question.dto.QuestionPostDto;
import pre045.board_service.question.dto.QuestionResponseDto;
import pre045.board_service.question.entity.Question;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-07T01:28:35+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question.QuestionBuilder question = Question.builder();

        question.title( questionPatchDto.getTitle() );
        question.questionContent( questionPatchDto.getQuestionContent() );

        return question.build();
    }

    @Override
    public Question questionPostDtoToQuestion(QuestionPostDto questionPostDto) {
        if ( questionPostDto == null ) {
            return null;
        }

        Question.QuestionBuilder question = Question.builder();

        question.title( questionPostDto.getTitle() );
        question.questionContent( questionPostDto.getQuestionContent() );

        return question.build();
    }

    @Override
    public QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        List<Answer> answers = null;
        List<QuestionComment> questionComments = null;
        Long questionId = null;
        String title = null;
        String questionContent = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        int view = 0;
        boolean checkAdopted = false;
        String questionUsername = null;
        int totalVotes = 0;

        List<Answer> list = question.getAnswers();
        if ( list != null ) {
            answers = new ArrayList<Answer>( list );
        }
        List<QuestionComment> list1 = question.getQuestionComments();
        if ( list1 != null ) {
            questionComments = new ArrayList<QuestionComment>( list1 );
        }
        questionId = question.getQuestionId();
        title = question.getTitle();
        questionContent = question.getQuestionContent();
        createdAt = question.getCreatedAt();
        modifiedAt = question.getModifiedAt();
        view = question.getView();
        checkAdopted = question.isCheckAdopted();
        questionUsername = question.getQuestionUsername();
        totalVotes = question.getTotalVotes();

        QuestionResponseDto questionResponseDto = new QuestionResponseDto( questionId, title, questionContent, createdAt, modifiedAt, view, checkAdopted, questionUsername, totalVotes, answers, questionComments );

        return questionResponseDto;
    }

    @Override
    public List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> question) {
        if ( question == null ) {
            return null;
        }

        List<QuestionResponseDto> list = new ArrayList<QuestionResponseDto>( question.size() );
        for ( Question question1 : question ) {
            list.add( questionToQuestionResponseDto( question1 ) );
        }

        return list;
    }
}
