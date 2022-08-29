package pre045.board_service.answer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre045.board_service.answer.dto.AnswerDto;
import pre045.board_service.answer.entity.Answer;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostToAnswer(AnswerDto.Post postDto);
    Answer answerPatchToAnswer(AnswerDto.Patch patchDto);
    AnswerDto.Response answerToAnswerResponse(Answer answer);

}
