package pre045.board_service.comment.QComment;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionCommentMapper {
    QuestionComment questionCommentPostToComment(QuestionCommentDto.Post questionCommentPostDto);

    QuestionComment questionCommentPatchToComment(QuestionCommentDto.Patch questionCommentPatchDto);

    QuestionCommentDto.Response questionCommentToCommentResponse(QuestionComment questioncomment);



}