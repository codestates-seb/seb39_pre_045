package pre045.board_service.answer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.member.Member;
import pre045.board_service.member.MemberRepository;
import pre045.board_service.question.Question;
import pre045.board_service.question.QuestionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    private final MemberRepository memberRepository;

    public Answer createAnswer(Long memberId, Answer answer) {
        Long questionId = answer.getQuestion().getQuestionId();

        //중복 답변 작성인지 확인
        verifyDuplicateWriter(memberId, questionId);

        //작성한 멤버 가져와서 매핑
        Member member = verifyExistMember(memberId);
        answer.addMember(member);
        member.addAnswer(answer);

        answer.setAnswerUsername(member.getUsername());
        answer.setCreatedAt(LocalDateTime.now());

        //작성한 질문 가져와서 매핑
        //현재 PostDto로 Question을 받고 있어서 불필요
        Question question = verifyExistQuestion(questionId);
        answer.addQuestion(question);
        question.addAnswer(answer);

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer) {
        Long answerId = answer.getAnswerId();

        Answer foundAnswer = answerRepository.findById(answerId).orElseThrow(() -> new AnswerException("유효한 답변이 아닙니다"));

        Long postMemberId = foundAnswer.getMember().getMemberId(); //요청 받은 Answer의 answerId로 찾아온 MemberId
        Long editMemberId = answer.getMember().getMemberId(); //요청 받은 Answer의 MemberId

        verifySameWriter(postMemberId, editMemberId);

        foundAnswer.setAnswerContent(answer.getAnswerContent());
        foundAnswer.setModifiedAt(LocalDateTime.now());
        foundAnswer.setAnswerUsername(answer.getMember().getUsername());

        return answerRepository.save(foundAnswer);
    }

    public void deleteAnswer(Long answerId) {
        Answer foundAnswer = verifyExistAnswer(answerId);
        answerRepository.delete(foundAnswer);
    }

    public void adoptAnswer(Long answerId) {
        Answer foundAnswer = verifyExistAnswer(answerId);
        Question foundQuestion = verifyIfAdopted(foundAnswer);

        foundAnswer.setAdopted(true);
        foundQuestion.setCheckAdopted(true);

        foundAnswer.addQuestion(foundQuestion);
        foundQuestion.addAnswer(foundAnswer);

        answerRepository.save(foundAnswer);
    }

    //이미 채택한 질문인지 확인
    private Question verifyIfAdopted(Answer answer) {
        Question foundQuestion = verifyExistQuestion(answer.getQuestion().getQuestionId());
        if (foundQuestion.isCheckAdopted()) {
            throw new AnswerException("답변 채택은 한번만 가능합니다.");
        }
        return foundQuestion;
    }


    //존재하는 멤버인지 확인
    private Member verifyExistMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NullPointerException("답변은 회원만 작성할 수 있습니다."));
    }

    //존재하는 질문인지 확인
    private Question verifyExistQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new NullPointerException("질문이 삭제되어 답변을 등록할 수 없습니다."));
    }

    //존재하는 답변인지 확인
    private Answer verifyExistAnswer(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new NullPointerException("존재하는 답변이 아닙니다"));
    }

    //작성자 == 수정자 검증
    private void verifySameWriter(Long addMemberId, Long editMemberId) {
        if (!addMemberId.equals(editMemberId)) {
            throw new AnswerException("답변 작성자만 수정할 수 있습니다.");
        }
    }

    //중복 답변 등록 검증
    private void verifyDuplicateWriter(Long memberId, Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        optionalQuestion.ifPresent(
                question -> question.getAnswers().stream()
                        .filter(answer -> answer.getMember().getMemberId().equals(memberId))
                        .findAny()
                        .ifPresent(duplicate -> {
                            throw new AnswerException("이미 등록된 답변이 있습니다. 기존 답변을 수정해주세요");
                        }));
    }


}
