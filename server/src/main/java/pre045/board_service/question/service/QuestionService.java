package pre045.board_service.question.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
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

// <------------------------------------------------------------------------------->

    public Question createQuestion(Question question) {
        // 유효성 검사 (질문 게시물 작성하는 유저가 회원인지)
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
        // 유효성 검사 (질문 게시물이 존재하는지)
        Question findByQuestionId = findVerifiedQuestion(question.getQuestionId());

        // Optional : NPE, 널포인터익셉션을 방지, 널 값이 와도 오류일으키지 않게끔 하는 래퍼 클래스
        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findByQuestionId.setTitle(title));

        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(questionContent -> findByQuestionId.setQuestionContent(questionContent));

        return questionRepository.save(findByQuestionId);

    }


    public Question findQuestion(long questionId) {
        // GET 요청에서 question 조회
        return findVerifiedQuestion(questionId);
    }


    private Question findVerifiedQuestion(long questionId) {
        // 질문 게시물이 존재하는지 questionId 로 검사
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        // 없으면 exception 리턴
        return optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page,size,
                Sort.by("questionId").descending()));
    }

    public void deleteQuestion(long questionId) {
        Question findQuestionDel = findVerifiedQuestion(questionId);

        questionRepository.delete(findQuestionDel);
    }
}
