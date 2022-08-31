package pre045.board_service.vote.question_vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.vote.question_vote.dto.QuestionVoteDto;
import pre045.board_service.vote.question_vote.entity.QuestionVote;
import pre045.board_service.vote.question_vote.mapper.QuestionVoteMapper;
import pre045.board_service.vote.question_vote.service.QuestionVoteService;

@RestController
@RequestMapping("/questions/{question-id}")
@RequiredArgsConstructor
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;
    private final QuestionVoteMapper mapper;

    @PostMapping("/up")
    public ResponseEntity upQuestion(@RequestBody QuestionVoteDto.Post post) {
        QuestionVote questionVote = mapper.questionVotePostToQuestionVote(post);
        QuestionVote upVote = questionVoteService.upVote(post.getMemberId(), questionVote);
        QuestionVoteDto.Response response = mapper.questionVoteToQuestionVoteResponse(upVote);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PostMapping("/down")
    public ResponseEntity downQuestion(@RequestBody QuestionVoteDto.Post post) {
        QuestionVote questionVote = mapper.questionVotePostToQuestionVote(post);
        QuestionVote upVote = questionVoteService.downVote(post.getMemberId(), questionVote);
        QuestionVoteDto.Response response = mapper.questionVoteToQuestionVoteResponse(upVote);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
}
