package pre045.board_service.question.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre045.board_service.question.dto.QuestionPatchDto;
import pre045.board_service.question.dto.QuestionPostDto;
import pre045.board_service.question.dto.QuestionResponseDto;
import pre045.board_service.question.entity.Question;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {

    // 매핑된 객체타입  매퍼메소드(매핑할 객체 타입, 매개변수)
    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);

    Question questionPostDtoToQuestion(QuestionPostDto questionPostDto);

    // Response 는 클라이언트로 넘겨줘야하기 때문에 역으로 Question 으로 매핑.
    QuestionResponseDto questionToQuestionResponseDto(Question question);

}
