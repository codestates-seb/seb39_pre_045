package pre045.board_service.question.service;

import org.aspectj.asm.AsmManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.entity.Member;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class QuestionService {

    private final MemberService memberService;
    private final QuestionRepository questionRepository;

    public QuestionService(MemberService memberService, QuestionRepository questionRepository) {
        this.memberService = memberService;
        this.questionRepository = questionRepository;
    }

// <------------------------------------------------------------------------------->

    public Question createQuestion(Question question) {
        // 유효성 검사 (질문 게시물 작성하는 유저가 회원인지)
        Member member = memberVerifyQuestion(question);

        question.setMember(member);
        question.setUsername(member.getUsername());

        // 생성날짜 추가
        question.setCreatedAt(LocalDateTime.now());

        // 조회수 증가
        question.setView(question.getView()+ 1);

        return saveQuestion(question);
    }


    private Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    private Member memberVerifyQuestion(Question question) {
        // 질문을 작성하는 유저가 회원인지 아닌지 여부
        return memberService.findVerifiedMember(question.getMember().getMemberId());
    }


    public Question updateQuestion(Question question) {
        // 유효성 검사 (질문 게시물이 존재하는지)
        Question findByQuestionId = findVerifiedQuestion(question.getQuestionId());

        Member member = memberVerifyQuestion(question);

        question.setMember(member);
        question.setUsername(member.getUsername());

        // Optional : NPE, 널포인터익셉션을 방지, 널 값이 와도 오류일으키지 않게 하는 래퍼 클래스
        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findByQuestionId.setTitle(title));

        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(questionContent -> findByQuestionId.setQuestionContent(questionContent));

        findByQuestionId.setModifiedAt(LocalDateTime.now());

        return saveQuestion(findByQuestionId);

    }

    public void deleteQuestion(long questionId) {
        Question findQuestionDel = findVerifiedQuestion(questionId);

        questionRepository.delete(findQuestionDel);
    }


    public Question findQuestion(long questionId) {
        // GET 요청에서 question 조회
        return findVerifiedQuestion(questionId);
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }  // Todo 파라미터 수정 요망

    private Question findVerifiedQuestion(long questionId) {
        // 질문 게시물이 존재하는지 questionId 로 검사
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        // 없으면 exception 리턴
        return optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }


    //Todo 페이지 검색


}
