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

    /**
     * 중복 답변 금지
     * 매핑 후 닉네임, 작성 시간 추가해 return
     * @param memberId - 작성자
     * @param answer - content
     * @return
     */
    public Answer createAnswer(Long memberId, Answer answer) {
        Long questionId = answer.getQuestion().getQuestionId();

        //중복 답변 작성인지 확인
        verifyDuplicateWriter(memberId, questionId);

        //작성한 멤버 가져와서 매핑
        Member member = verifyExistMember(memberId);
        answer.setMember(member);

        //작성한 질문 가져와서 매핑
        Question question = verifyExistQuestion(questionId);
        answer.setQuestion(question);

        //닉네임, 작성 시간 추가
        answer.setAnswerUsername(member.getUsername());
        answer.setCreatedAt(LocalDateTime.now());

        return answerRepository.save(answer);
    }


    /**
     * 작성자 == 수정자 2차 검증
     * 매핑 후 username, content, modifiedAt 담아 return
     *
     * @param memberId 수정하려는 멤버
     * @param answer answerId, answerContent 받아오기
     * @return
     */
    public Answer updateAnswer(Long memberId, Answer answer) {
        Long answerId = answer.getAnswerId();
        Answer foundAnswer = verifyExistAnswer(answerId); //기존 작성된 답변 가져오기

        Long postMemberId = foundAnswer.getMember().getMemberId(); //기존 답변의 작성자
        verifySameWriter(postMemberId, memberId); //백에서 작성자 == 수정자 2차 검증

        Member foundMember = verifyExistMember(memberId);
        Long questionId = answer.getQuestion().getQuestionId();
        Question foundQuestion = verifyExistQuestion(questionId);

        //매핑
        foundAnswer.setMember(foundMember);
        foundAnswer.setQuestion(foundQuestion);

        foundAnswer.setAnswerContent(answer.getAnswerContent());
        foundAnswer.setModifiedAt(LocalDateTime.now());
        foundAnswer.setAnswerUsername(foundMember.getUsername());

        return answerRepository.save(foundAnswer);
    }

    /**
     * 채택된 답변은 삭제 불가능 처리
     *
     * @param answerId 답변 식별자
     */
    public void deleteAnswer(Long answerId) {
        Answer foundAnswer = verifyExistAnswer(answerId);
        if(foundAnswer.isAdopted()) {
            throw new AnswerException("채택된 답변은 삭제할 수 없습니다.");
        }
        answerRepository.delete(foundAnswer);
    }

    /**
     * 질문에 이미 채택된 답변이 없다면 채택하도록 처리
     * @param answerId
     */
    public void adoptAnswer(Long answerId) {
        Answer foundAnswer = verifyExistAnswer(answerId);
        Question foundQuestion = verifyIfAdopted(foundAnswer);

        foundAnswer.setAdopted(true);
        foundQuestion.setCheckAdopted(true);

        foundAnswer.setQuestion(foundQuestion);

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
