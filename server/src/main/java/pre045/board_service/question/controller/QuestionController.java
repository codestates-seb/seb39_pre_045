package pre045.board_service.question.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.question.service.QuestionService;
import pre045.board_service.question.entity.Question;

@RestController
@RequestMapping("/questions")
@Validated
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/{member-id}/add")
    public ResponseEntity addQuestion() {


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{question-id}/edit")
    public ResponseEntity editQuestion() {


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/questions/{question-id}")
    public ResponseEntity deleteQuestion() {


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
