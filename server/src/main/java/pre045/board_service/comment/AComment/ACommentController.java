package pre045.board_service.comment.AComment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.dto.SingleResponseDto;


@RestController
@RequestMapping("/answers/{answer-id}/comments")
@AllArgsConstructor
@Validated
@Slf4j
public class ACommentController {

    private final ACommentService ACommentService;

    private final ACommentMapper mapper;


    @PostMapping
    public ResponseEntity addAComment(@RequestBody ACommentDto.Post postDto) {

       AComment aComment = mapper.commentPostToComment(postDto);

       AComment createdAComment = ACommentService.createAComment(postDto.getMemberId(), aComment);

       ACommentDto.Response response = mapper.commentToCommentResponse(createdAComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED); // 컨트롤러 리턴값 추가 post, patch
    }

    @PatchMapping("/{a-comment-id}")
    public ResponseEntity editAComment(@PathVariable("a-comment-id") Long aCommentId, @RequestBody ACommentDto.Patch patchDto) {
        patchDto.setCommentId(aCommentId);

        AComment aComment = mapper.commentPatchToComment(patchDto);
        AComment editedAComment = ACommentService.updateAComment(patchDto.getMemberId(), aComment);
        ACommentDto.Response response = mapper.commentToCommentResponse(editedAComment);


        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{a-comment-id}")
    public ResponseEntity deleteAComment(@PathVariable("a-comment-id") Long aCommentId) { //코멘트 아이디 확인후 삭제
        ACommentService.deleteComment(aCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
