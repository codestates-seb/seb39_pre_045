package pre045.board_service.comment.AComment;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ACommentMapper {
    AComment commentPostToComment(ACommentDto.Post commentDto);

    AComment commentPatchToComment(ACommentDto.Patch commentDto);

    ACommentDto.Response commentToCommentResponse(AComment aComment);// 이해못함



}
