package pre045.board_service.answer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.answer.dto.AnswerDto;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.answer.mapper.AnswerMapper;
import pre045.board_service.answer.service.AnswerService;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.member.token.config.SecurityUtil;

import javax.validation.Valid;


@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper mapper;

    /**
     *
     * @param postDto - memberId, question, answerContent
     * @return - CREATED / username, answerContent, createdAt, modifiedAt
     */
    @PostMapping
    public ResponseEntity addAnswer(@RequestBody @Valid AnswerDto.Post postDto) {
        Answer answer = mapper.answerPostToAnswer(postDto);
        Answer createdAnswer = answerService.createAnswer(SecurityUtil.getCurrentMemberId(), answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(createdAnswer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);

    }


    /**
     * 답변 수정
     * @param answerId 답변을 등록할 질문
     * @param patchDto - answerId, Member, Question, answerContent
     * @return - OK / username, answerContent, createdAt, modifiedAt
     *
     */
    @PatchMapping("/{answer-id}")
    public ResponseEntity editAnswer(@PathVariable("answer-id") Long answerId, @RequestBody @Valid AnswerDto.Patch patchDto) {
        patchDto.setAnswerId(answerId);

        Answer answer = mapper.answerPatchToAnswer(patchDto);
        Answer editedAnswer = answerService.updateAnswer(SecurityUtil.getCurrentMemberId(), answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(editedAnswer);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    /**
     *
     * @param answerId - 삭제할 답변 ID
     * @return - NO_CONTENT
     */
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") Long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @param answerId - 채택할 답변 ID
     * @return - CREATED
     */
    @PostMapping("/{answer-id}/adopt/{question-id}")
    public ResponseEntity adoptAnswer(@PathVariable("answer-id") Long answerId, @PathVariable("question-id") Long questionId) {
        answerService.adoptAnswer(answerId, questionId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
