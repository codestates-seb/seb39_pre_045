package pre045.board_service.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.dto.MultiResponseDto;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.member.token.config.SecurityUtil;
import pre045.board_service.question.dto.QuestionPatchDto;
import pre045.board_service.question.dto.QuestionPostDto;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.mapper.QuestionMapper;
import pre045.board_service.question.service.QuestionService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/questions")
@Validated
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper mapper;


    @PostMapping
    public ResponseEntity addQuestion(@Valid @RequestBody QuestionPostDto questionPostDto) {

        Question question = mapper.questionPostDtoToQuestion(questionPostDto);
        Question createQuestion = questionService.createQuestion(question, SecurityUtil.getCurrentMemberId());

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(createQuestion)), HttpStatus.CREATED);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity editQuestion(@PathVariable("question-id") @Positive long questionId,
                                       @Valid @RequestBody QuestionPatchDto questionPatchDto) {

        Question question = mapper.questionPatchDtoToQuestion(questionPatchDto);
        question.setQuestionId(questionId);

        Question updateQuestion = questionService.updateQuestion(question, SecurityUtil.getCurrentMemberId());

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(updateQuestion)), HttpStatus.OK);
    }

    @GetMapping("/{question-id}") // 단일 질문 조회
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {
        Question question = questionService.findQuestion(questionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)),
                HttpStatus.OK);
    }

    //검색
    @GetMapping("/search")
    public ResponseEntity findQuestions(@RequestParam String q, @RequestParam String sort, @RequestParam int page) {
        MultiResponseDto questions = questionService.findQuestions(q, sort, page - 1);

        return new ResponseEntity(questions, HttpStatus.OK);
    }


    //질문 리스트 반환
    @GetMapping
    public ResponseEntity getQuestions(@RequestParam int page, @RequestParam String sort, @RequestParam String filters) {

        MultiResponseDto filterAndSortQuestions = questionService.getFilterAndSortQuestions(page - 1, sort, filters);

        return new ResponseEntity<>(filterAndSortQuestions, HttpStatus.OK);
    }


    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
