package pre045.board_service.comment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@Validated
@Slf4j
public class CommentController {

    @PostMapping("/{comment-id}/add")
    public ResponseEntity addComment() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}/edit")
    public ResponseEntity editComment() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
