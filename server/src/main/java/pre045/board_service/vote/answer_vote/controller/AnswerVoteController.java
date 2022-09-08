package pre045.board_service.vote.answer_vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.vote.answer_vote.service.AnswerVoteService;

@RestController
@RequestMapping("/answers/{answer-id}")
@RequiredArgsConstructor
public class AnswerVoteController {

    private final AnswerVoteService answerVoteService;


    @PostMapping("/up")
    public ResponseEntity upAnswer(@PathVariable("answer-id") Long answerId) {

        return new ResponseEntity(new SingleResponseDto<>(answerVoteService.upVote(answerId)), HttpStatus.CREATED);
    }

    @PostMapping("/down")
    public ResponseEntity downAnswer(@PathVariable("answer-id") Long answerId) {
        return new ResponseEntity(new SingleResponseDto<>(answerVoteService.downVote(answerId)), HttpStatus.CREATED);
    }
}
