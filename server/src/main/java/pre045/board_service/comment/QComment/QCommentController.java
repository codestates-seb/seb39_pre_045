package pre045.board_service.comment.QComment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.comment.AComment.AComment;
import pre045.board_service.comment.AComment.ACommentDto;
import pre045.board_service.comment.AComment.ACommentMapper;
import pre045.board_service.comment.AComment.ACommentService;
import pre045.board_service.dto.SingleResponseDto;


@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@Validated
@Slf4j
public class QCommentController {

    private final QCommentService qCommentService;

    private final QCommentMapper mapper;


    @PostMapping
    public ResponseEntity addQComment(@RequestBody QCommentDto.Post postDto) {

        QComment qComment = mapper.commentPostToComment(postDto);

        QComment createdQComment = qCommentService.createQComment(postDto.getMemberId(), qComment);

        QCommentDto.Response response = mapper.commentToCommentResponse(createdQComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED); // 컨트롤러 리턴값 추가 post, patch
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity editQComment(@PathVariable("comment-id") Long commentId, @RequestBody QCommentDto.Patch patchDto) {
        patchDto.setCommentId(commentId);

        QComment AComment = mapper.commentPatchToComment(patchDto);
        QComment editedAComment = qCommentService.updateQComment(patchDto.getMemberId(), AComment);
        QCommentDto.Response response = mapper.commentToCommentResponse(editedAComment);


        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteQComment(@PathVariable("comment-Id") Long qCommentId) { //코멘트 아이디 확인후 삭제
        qCommentService.deleteQComment(qCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}