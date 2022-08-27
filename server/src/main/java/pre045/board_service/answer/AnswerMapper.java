package pre045.board_service.answer;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostToAnswer(AnswerDto.Post postDto);

    Answer answerPatchToAnswer(AnswerDto.Patch patchDto);

    AnswerDto.Response answerToAnswerResponse(Answer answer);

}
