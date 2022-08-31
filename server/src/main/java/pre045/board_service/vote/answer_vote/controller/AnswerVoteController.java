package pre045.board_service.vote.answer_vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.vote.answer_vote.dto.AnswerVoteDto;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;
import pre045.board_service.vote.answer_vote.mapper.AnswerVoteMapper;
import pre045.board_service.vote.answer_vote.service.AnswerVoteService;

@RestController
@RequestMapping("/answers/{answer-id}")
@RequiredArgsConstructor
public class AnswerVoteController {

    private final AnswerVoteService answerVoteService;
    private final AnswerVoteMapper mapper;

    @PostMapping("/up")
    public ResponseEntity upAnswer(@RequestBody AnswerVoteDto.Post post) {
        AnswerVote answerVote = mapper.answerVotePostToAnswerVote(post);
        AnswerVote upVote = answerVoteService.upVote(post.getMemberId(), answerVote);
        AnswerVoteDto.Response response = mapper.answerVoteToAnswerVoteResponse(upVote);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PostMapping("/down")
    public ResponseEntity downAnswer(@RequestBody AnswerVoteDto.Post post) {
        AnswerVote answerVote = mapper.answerVotePostToAnswerVote(post);
        AnswerVote downVote = answerVoteService.downVote(post.getMemberId(), answerVote);
        AnswerVoteDto.Response response = mapper.answerVoteToAnswerVoteResponse(downVote);

        return new ResponseEntity(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
}
