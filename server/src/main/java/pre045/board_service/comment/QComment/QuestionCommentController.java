package pre045.board_service.comment.QComment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.member.token.config.SecurityUtil;

@RestController
@RequestMapping("/questions/{question-id}/comments")
@AllArgsConstructor
@Validated
@Slf4j
public class QuestionCommentController {

    private final QuestionCommentService questionCommentService;

    private final QuestionCommentMapper mapper;


    @PostMapping
    public ResponseEntity addQuestionComment(@RequestBody QuestionCommentDto.Post questionCommentPostDto) {

        QuestionComment questionComment = mapper.questionCommentPostToComment(questionCommentPostDto);

        QuestionComment createdQuestionComment = questionCommentService.createQuestionComment(SecurityUtil.getCurrentMemberId(), questionComment);

        QuestionCommentDto.Response response = mapper.questionCommentToCommentResponse(createdQuestionComment);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED); // 컨트롤러 리턴값 추가 post, patch
    }

    @PatchMapping("/{q-comment-id}")
    public ResponseEntity editQuestionComment(@PathVariable("q-comment-id") Long questionCommentId, @RequestBody QuestionCommentDto.Patch questionCommentPatchDto) {
        questionCommentPatchDto.setQuestionCommentId(questionCommentId); // dto 통해서 answer 코멘트 아이디

        QuestionComment questionComment = mapper.questionCommentPatchToComment(questionCommentPatchDto);
        QuestionComment editedQuestionComment = questionCommentService.updateQuestionComment(SecurityUtil.getCurrentMemberId(), questionComment);
        QuestionCommentDto.Response response = mapper.questionCommentToCommentResponse(editedQuestionComment);


        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/{q-comment-id}")
    public ResponseEntity deleteQuestionComment(@PathVariable("q-comment-id") Long questionCommentId) { //코멘트 아이디 확인후 삭제
        questionCommentService.deleteQuestionComment(questionCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
