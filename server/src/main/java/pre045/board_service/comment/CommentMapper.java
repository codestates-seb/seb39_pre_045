package pre045.board_service.comment;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre045.board_service.comment.dto.CommentDto;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    Comment commentPostToComment(CommentDto.Post commentDto);

    Comment commentPatchToComment(CommentDto.Patch commentDto);

    CommentDto.Response commentToCommentResponse(Comment comment);// 이해못함



}
