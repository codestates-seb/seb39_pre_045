package pre045.board_service.question.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.service.MemberService;
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
        Member member = new Member("abc@naver.com","jp","aaqqwweer","Male",20);

//        Member member = memberVerifyQuestion(question);

        question.setMember(member);
        question.setUsername(member.getUsername());

        question.setCreatedAt(LocalDateTime.now());

        return saveQuestion(question);
    }


    private Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }



    public Question updateQuestion(Question question, long memberId) {

        // 유효성 검사 (질문 게시물이 존재하는지)
        Question findQuestion = findVerifiedQuestionById(question.getQuestionId());

        // 유효성 검사 (작성자가 회원인지)
        Member member = memberVerifyQuestion(memberId);

        findQuestion.setMember(member);
        findQuestion.setUsername(member.getUsername());

        findQuestion.setModifiedAt(LocalDateTime.now());

        // Optional : NPE, 널포인터익셉션을 방지, 널 값이 와도 오류일으키지 않게 하는 래퍼 클래스
        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));

        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(questionContent -> findQuestion.setQuestionContent(questionContent));


        return saveQuestion(findQuestion);

    }

    public void deleteQuestion(long questionId) {
        Question findQuestionDel = findVerifiedQuestionById(questionId);

        questionRepository.delete(findQuestionDel);
    }


    public Question findQuestion(long questionId) {
        Question findQuestionGet = findVerifiedQuestionById(questionId);
        findQuestionGet.setView(findQuestionGet.getView()+1);

        return findQuestionGet;
    }


    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }

    public Page<Question> findQuestions(int page, int size, String sort){
        Sort sorting;
        switch(sort){
            case "newest":
                sorting = Sort.by("createdAt").descending();
                break;
            case "oldest":
                sorting = Sort.by("createdAt").ascending();
                break;
            case "votes":
                sorting = Sort.by("totalVotes").descending();
                break;
            default:
                sorting = Sort.by("questionId").descending();
        }

        return questionRepository.findAll(PageRequest.of(page, size, sorting));
    }

    private Member memberVerifyQuestion(Long memberId) {
        // 질문을 작성하는 유저가 회원인지 아닌지 여부
        return memberService.findVerifiedMember(memberId);
    }


    private Question findVerifiedQuestionById(long questionId) {
        // 질문 게시물이 존재하는지 questionId 로 검사
        return questionRepository.findById(questionId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }





}

// Todo 파라미터 수정 요망
// Todo 페이지 검색
