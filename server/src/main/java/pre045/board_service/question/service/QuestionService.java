package pre045.board_service.question.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;

import java.util.Optional;

@Transactional
@Service
public class QuestionService {

//    private final MemberService memberService;
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Question createQuestion(Question question) {
        verifyQuestion(question);
        return saveQuestion(question);
    }

    private Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    private void verifyQuestion(Question question) {
        // 질문을 작성하는 유저가 회원인지 아닌지 여부
//        memberService.findVerifiedMember(question.getMember().getMemberId());
        
    }


    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(questionContent -> findQuestion.setQuestionContent(questionContent));
        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));

        return questionRepository.save(findQuestion);

    }

    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }

    public Question findQuestion(long questionId) {
        return findVerifiedQuestionById
    }
}
