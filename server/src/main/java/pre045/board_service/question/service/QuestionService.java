package pre045.board_service.question.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.dto.MultiResponseDto;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.service.MemberService;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    //검색
    public MultiResponseDto findQuestions(String q, String sort, int page) {
        List<Question> all = questionRepository.findAll();

        List<Question> foundQuestions = all.stream()
                .filter(question -> question.getTitle().contains(q) || question.getQuestionContent().contains(q))
                .sorted(foundQuestionSorting(sort))
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page, 20);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), foundQuestions.size());

        Page<Question> pageResult = new PageImpl<>(foundQuestions.subList(start, end), pageable, foundQuestions.size());

        return new MultiResponseDto<>(pageResult.getContent(), pageResult);


    }

    //정렬
    public Comparator<Question> foundQuestionSorting(String sort) {
        if (!sort.isEmpty()) {
            switch (sort) {
                case "votes":
                    return (o1, o2) -> o2.getTotalVotes() - o1.getTotalVotes();
                case "newest":
                    return ((o1, o2) -> Long.compare(o2.getQuestionId(), o1.getQuestionId()));
            }
        }
        return (o1, o2) -> o2.getTotalVotes() - o1.getTotalVotes();
    }

    //전체 목록 조회
    public MultiResponseDto getFilterAndSortQuestions(int page, String sort, String filters) {
        List<Question> all = questionRepository.findAll();
        List<Question> filterAndSorted = new ArrayList<>();

        if (!filters.isEmpty()) {
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

        Pageable pageable = PageRequest.of(page, 20);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), filterAndSorted.size());

        Page<Question> pageResult = new PageImpl<>(filterAndSorted.subList(start, end), pageable, filterAndSorted.size());

        return new MultiResponseDto<>(pageResult.getContent(), pageResult);
    }

    public Comparator<Question> sortQuestions(String sort) {
        if (!sort.isEmpty()) {
            switch (sort) {
                case "votes":
                    return (o1, o2) -> o2.getTotalVotes() - o1.getTotalVotes();
                case "answers":
                    return ((o1, o2) -> o2.getAnswers().size() - o1.getAnswers().size());
                case "oldest":
                    return (Comparator.comparingLong(Question::getQuestionId));
            }
        }
        return ((o1, o2) -> Long.compare(o2.getQuestionId(), o1.getQuestionId()));
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


//    public Page<Question> findQuestions(int page, int size) {
//        return questionRepository.findAll(PageRequest.of(page, size,
//                Sort.by("questionId").descending()));
//    }
//
//    public Page<Question> findQuestions(int page, int size, String sort){
//        Sort sorting;
//        switch(sort){
//            case "newest":
//                sorting = Sort.by("createdAt").descending();
//                break;
//            case "oldest":
//                sorting = Sort.by("createdAt").ascending();
//                break;
//            case "votes":
//                sorting = Sort.by("totalVotes").descending();
//                break;
//            default:
//                sorting = Sort.by("questionId").descending();
//        }
//
//        return questionRepository.findAll(PageRequest.of(page, size, sorting));
//    }

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
