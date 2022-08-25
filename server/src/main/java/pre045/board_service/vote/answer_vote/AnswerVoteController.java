package pre045.board_service.vote.answer_vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Slf4j
public class AnswerVoteController {

    @PatchMapping("/questions/{question-id}/upvote")
    public ResponseEntity upVoteQuestion() {
        return new ResponseEntity<>(HttpStatus.TEMPORARY_REDIRECT); //?
    }
}
