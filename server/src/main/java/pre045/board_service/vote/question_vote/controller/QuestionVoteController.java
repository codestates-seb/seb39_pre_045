package pre045.board_service.vote.question_vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.vote.question_vote.service.QuestionVoteService;

@RestController
@RequestMapping("/questions/{question-id}")
@RequiredArgsConstructor
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;

    @PostMapping("/up")
    public ResponseEntity upQuestion(@PathVariable("question-id") Long questionId) {

        return new ResponseEntity(new SingleResponseDto<>(questionVoteService.upVote(questionId)), HttpStatus.CREATED);
    }

    @PostMapping("/down")
    public ResponseEntity downQuestion(@PathVariable("question-id") Long questionId) {

        return new ResponseEntity(new SingleResponseDto<>(questionVoteService.downVote(questionId)), HttpStatus.CREATED);
    }
}
