package pre045.board_service.comment.AComment;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerCommentMapper {
    AnswerComment answerCommentPostToComment(AnswerCommentDto.Post answerCommentPostDto);

    AnswerComment answerCommentPatchToComment(AnswerCommentDto.Patch answerCommentPatchDto);

    AnswerCommentDto.Response answerCommentToCommentResponse(AnswerComment answercomment);



}
