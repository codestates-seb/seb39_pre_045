package pre045.board_service.comment.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.comment.entity.Comment;
import pre045.board_service.comment.mapper.CommentMapper;
import pre045.board_service.comment.service.CommentService;
import pre045.board_service.comment.dto.CommentDto;


@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@Validated
@Slf4j
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper mapper;


    @PostMapping("/{comment-id}/add")
    public ResponseEntity addComment(@PathVariable("member-id") Long memberId, @RequestBody CommentDto.Post commentDto) {

       Comment comment = mapper.commentPostToComment(commentDto);

       Comment createdComment = commentService.createComment(memberId, comment);

       CommentDto.Response response = mapper.commentToCommentResponse(createdComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}/edit")
    public ResponseEntity editComment(@PathVariable("comment-id") Long commentId, @RequestBody CommentDto.Patch patchDto) {
        patchDto.setCommentId(commentId);

        Comment comment = mapper.commentPatchToComment(patchDto);
        Comment editedComment = commentService.updateComment(comment);
        CommentDto.Response response = mapper.commentToCommentResponse(editedComment);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") Long commentId) { //코멘트 아이디 확인후 삭제
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
