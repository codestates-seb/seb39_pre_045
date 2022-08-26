package pre045.board_service.vote.question_vote;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Slf4j
public class QuestionVoteController {

    @PatchMapping("/answers/{answer-id}/upvote")
    public ResponseEntity upVoteAnswer() {
        return new ResponseEntity<>(HttpStatus.TEMPORARY_REDIRECT); //?
    }
}
