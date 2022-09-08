package pre045.board_service.question.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import pre045.board_service.dto.MultiResponseDto;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.service.MemberService;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pre045.board_service.HomeController.sortQuestions;

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

    public Question createQuestion(Question question, long memberId) {

        // 유효성 검사 (질문 게시물 작성하는 유저가 회원인지)
        Member member = memberVerifyQuestion(memberId);

        question.setMember(member);
        question.setQuestionUsername(member.getUsername());

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
        findQuestion.setQuestionUsername(member.getUsername());

        findQuestion.setModifiedAt(LocalDateTime.now());

        // Optional : NPE, 널포인터익셉션을 방지, 널 값이 와도 오류일으키지 않게 하는 래퍼 클래스
        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));

        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(questionContent -> findQuestion.setQuestionContent(questionContent));


        return saveQuestion(findQuestion);

    }


    /**
     * 질문 검색
     *
     * @param q    - 검색 키워드
     * @param sort - 정렬 조건
     * @param page - 페이지 번호
     * @return - data, pageInfo
     */
    public MultiResponseDto findQuestions(String q, String sort, int page) {
        List<Question> all = questionRepository.findAll();

        if (sort.isEmpty()) {
            sort = "votes";
        }

        List<Question> foundQuestions = all.stream()
                .filter(question -> question.getTitle().contains(q) || question.getQuestionContent().contains(q))
                .sorted(sortQuestions(sort))
                .collect(Collectors.toList());

        Page<Question> pageResult = getPageResult(page, foundQuestions);

        return new MultiResponseDto<>(pageResult.getContent(), pageResult);


    }

    /**
     * 질문 전체(리스트) 조회
     * 기본 정렬 - 최신순
     *
     * @param page    - 페이지 번호
     * @param sort    - 정렬 조건
     * @param filters - 질문 필터
     * @return - data, pageInfo
     */
    public MultiResponseDto getFilterAndSortQuestions(int page, String sort, String filters) {
        List<Question> all = questionRepository.findAll();
        List<Question> filterAndSorted = new ArrayList<>();

        // filters 가 없을 때 -> 기존의 !filters 는 공백만 잡아주는데 반해, StringUtils는 null도 잡는다
        if (!StringUtils.isEmpty(filters)) {
            switch (filters) {
                case "noAdopt":
                    filterAndSorted = all.stream()
                            .filter(question -> !question.isCheckAdopted())
                            .sorted(sortQuestions(sort))
                            .collect(Collectors.toList());

                    break;
                case "noAnswers":
                    filterAndSorted = all.stream()
                            .filter(question -> question.getAnswers().isEmpty())
                            .sorted(sortQuestions(sort))
                            .collect(Collectors.toList());
                    break;
            }
        } else {
            filterAndSorted = all.stream()
                    .sorted(sortQuestions(sort))
                    .collect(Collectors.toList());
        }

        Page<Question> pageResult = getPageResult(page, filterAndSorted);

        return new MultiResponseDto<>(pageResult.getContent(), pageResult);
    }


    public void deleteQuestion(long questionId) {
        Question findQuestionDel = findVerifiedQuestionById(questionId);

        questionRepository.delete(findQuestionDel);
    }


    public Question findQuestion(long questionId) {
        Question findQuestionGet = findVerifiedQuestionById(questionId);
        findQuestionGet.setView(findQuestionGet.getView() + 1);

        return findQuestionGet;
    }


    /**
     * 페이징 처리
     *
     * @param page           - 페이지 번호
     * @param foundQuestions - 페이징 처리할 리스트
     * @return - 페이징 처리된 질문들
     */
    private Page<Question> getPageResult(int page, List<Question> foundQuestions) {
        Pageable pageable = PageRequest.of(page, 20);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), foundQuestions.size());

        Page<Question> pageResult = new PageImpl<>(foundQuestions.subList(start, end), pageable, foundQuestions.size());
        return pageResult;
    }

    private Member memberVerifyQuestion(Long memberId) {
        // 질문을 작성하는 유저가 회원인지 아닌지 여부
        return memberService.findVerifiedMember(memberId);
    }


    public Question findVerifiedQuestionById(long questionId) {
        // 질문 게시물이 존재하는지 questionId 로 검사
        return questionRepository.findById(questionId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

}
