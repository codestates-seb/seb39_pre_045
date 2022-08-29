package pre045.board_service.answer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
     * @return - CREATED / username, answerContent, createdAt, modifiedAt (항상 null이긴 한데 그럼 responseDto 따로 만들까요......)
     */
    @PostMapping("/add")
    public ResponseEntity addAnswer(@RequestBody AnswerDto.Post postDto) {
        Answer answer = mapper.answerPostToAnswer(postDto);
        Answer createdAnswer = answerService.createAnswer(postDto.getMemberId(), answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(createdAnswer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * 답변 수정
     * @param answerId 답변을 등록할 질문
     * @param patchDto - answerId, Member, Question, answerContent
     * @return - OK / username, answerContent, createdAt, modifiedAt
     *
     */
    @PatchMapping("/{answer-id}")
    public ResponseEntity editAnswer(@PathVariable("answer-id") Long answerId, @RequestBody AnswerDto.Patch patchDto) {
        patchDto.setAnswerId(answerId);

        Answer answer = mapper.answerPatchToAnswer(patchDto);
        Answer editedAnswer = answerService.updateAnswer(patchDto.getMemberId(), answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(editedAnswer);

        return new ResponseEntity<>(response, HttpStatus.OK);
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
    @PostMapping("/adopt/{answer-id}")
    public ResponseEntity adoptAnswer(@PathVariable("answer-id") Long answerId) {
        answerService.adoptAnswer(answerId);

        return new ResponseEntity(HttpStatus.CREATED);
    }


}
