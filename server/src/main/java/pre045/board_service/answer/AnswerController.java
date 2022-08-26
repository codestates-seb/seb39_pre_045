package pre045.board_service.answer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController {

    @PostMapping("/{member-id}/add")
    public ResponseEntity addAnswer() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}/edit")
    public ResponseEntity editAnswer() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
