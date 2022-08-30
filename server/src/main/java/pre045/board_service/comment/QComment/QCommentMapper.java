package pre045.board_service.comment.QComment;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QCommentMapper {
    QComment commentPostToComment(QCommentDto.Post commentDto);

    QComment commentPatchToComment(QCommentDto.Patch commentDto);

    QCommentDto.Response commentToCommentResponse(QComment qComment);



}
