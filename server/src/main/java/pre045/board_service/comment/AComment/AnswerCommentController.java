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
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;

    private final AnswerCommentMapper mapper;


    @PostMapping
    public ResponseEntity addAnswerComment(@RequestBody AnswerCommentDto.Post answerCommentPostDto) {

        AnswerComment answerComment = mapper.answerCommentPostToComment(answerCommentPostDto);
        AnswerComment createdAnswerComment = answerCommentService.createAnswerComment(answerCommentPostDto.getMemberId(), answerComment);

        AnswerCommentDto.Response response = mapper.answerCommentToCommentResponse(createdAnswerComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED); // 컨트롤러 리턴값 추가 post, patch
    }

    @PatchMapping("/{a-comment-id}")
    public ResponseEntity editAnswerComment(@PathVariable("a-comment-id") Long answerCommentId, @RequestBody AnswerCommentDto.Patch answerCommentPatchDto) {

        answerCommentPatchDto.setAnswerCommentId(answerCommentId);


        AnswerComment answerComment = mapper.answerCommentPatchToComment(answerCommentPatchDto);
        AnswerComment editedAnswerComment = answerCommentService.updateAnswerComment(answerCommentPatchDto.getMemberId(), answerComment);
        AnswerCommentDto.Response response = mapper.answerCommentToCommentResponse(editedAnswerComment);


        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{a-comment-id}")
    public ResponseEntity deleteAnswerComment(@PathVariable("a-comment-id") Long answerCommentId) { //코멘트 아이디 확인후 삭제
        answerCommentService.deleteAnswerComment(answerCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
