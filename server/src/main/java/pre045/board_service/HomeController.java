package pre045.board_service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;

import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final QuestionRepository questionRepository;

    /**
     * 기본 절렬 -최신순
     *
     * @param sort - 정렬 조건
     * @return - 20개
     */
    @GetMapping("/")
    public ResponseEntity home(@RequestParam String sort) {
        List<Question> all = questionRepository.findAll();
        all.sort(sortQuestions(sort));

        List<Question> questions = all.subList(0, 20);

        return new ResponseEntity(new SingleResponseDto<>(questions), HttpStatus.OK);
    }

    public static Comparator<Question> sortQuestions(String sort) {
        if (!sort.isEmpty()) {
            switch (sort) {
                case "votes":
                    return (o1, o2) -> o2.getTotalVotes() - o1.getTotalVotes();
                case "answers":
                    return ((o1, o2) -> o2.getAnswers().size() - o1.getAnswers().size());
                case "newest":
                    return ((o1, o2) -> Long.compare(o2.getQuestionId(), o1.getQuestionId()));
            }
        }
        return ((o1, o2) -> Long.compare(o2.getQuestionId(), o1.getQuestionId()));
    }

}
