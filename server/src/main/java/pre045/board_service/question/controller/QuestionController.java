package pre045.board_service.question.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.dto.MultiResponseDto;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.question.dto.QuestionPatchDto;
import pre045.board_service.question.dto.QuestionPostDto;
import pre045.board_service.question.mapper.QuestionMapper;
import pre045.board_service.question.service.QuestionService;
import pre045.board_service.question.entity.Question;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper mapper;


    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity addQuestion(@Valid @RequestBody QuestionPostDto questionPostDto) {
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponseDto(question)), HttpStatus.CREATED);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity editQuestion(@PathVariable("question-id") @Positive long questionId,
                                       @Valid @RequestBody QuestionPatchDto questionPatchDto) {

        Question question = mapper.questionPatchDtoToQuestion(questionPatchDto);
        question.setQuestionId(questionId);

        Question updateQuestion = questionService.updateQuestion(question, questionPatchDto.getMemberId());

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

    // Todo 게시물 검색 기능 구현 요망 (리퀘스트 파람), 정렬 기능 추가
    // Todo API 명세서 참조

//    @GetMapping  // 전체 질문 조회
//    public ResponseEntity getQuestions(@Positive @RequestParam int page) {
//        int size = 10;
//        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
//        List<Question> questions = pageQuestions.getContent();
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions), pageQuestions),
//                HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity getQuestionsSort(@Positive @RequestParam int page,
                                                 @RequestParam String sort){
        int size = 10;
        Page<Question> pageQuestions = questionService.findQuestions(page -1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponseDtos(questions), pageQuestions),
                HttpStatus.OK);

    }




    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
