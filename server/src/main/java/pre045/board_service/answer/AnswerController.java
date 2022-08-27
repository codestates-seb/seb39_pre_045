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
     * 답변 등록
     * @param memberId - username 표기를 위해 받아옴
     *                 - 로그인 기능 구현 후 다른 URI와의 혼동을 피하기 위해 빼는 것 고려
     * @param postDto - Question, answerContent
     * @return
     *
     * URL - /answers/add
     *
     */
    @PostMapping("/{member-id}/add")
    public ResponseEntity addAnswer(@PathVariable("member-id") Long memberId, @RequestBody AnswerDto.Post postDto) {
        Answer answer = mapper.answerPostToAnswer(postDto);
        Answer createdAnswer = answerService.createAnswer(memberId, answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(createdAnswer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * 답변 수정
     * @param answerId 답변을 등록할 질문
     * @param patchDto - answerId, Member, Question, answerContent
     * @return
     *
     */
    @PatchMapping("/{answer-id}/edit")
    public ResponseEntity editAnswer(@PathVariable("answer-id") Long answerId, @RequestBody AnswerDto.Patch patchDto) {
        //왜 쓰는지 까먹음!
        patchDto.setAnswerId(answerId);

        Answer answer = mapper.answerPatchToAnswer(patchDto);
        Answer editedAnswer = answerService.updateAnswer(answer);
        AnswerDto.Response response = mapper.answerToAnswerResponse(editedAnswer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") Long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
