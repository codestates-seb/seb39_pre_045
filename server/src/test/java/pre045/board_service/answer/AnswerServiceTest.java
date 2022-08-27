package pre045.board_service.answer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.member.Member;
import pre045.board_service.member.MemberRepository;
import pre045.board_service.question.Question;
import pre045.board_service.question.QuestionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;

    private Answer answer;
    private Member member1;
    private Question question1;


    @BeforeEach
    void init() {
        //given
        answer = new Answer("답변 내용", LocalDateTime.now(), null);
        answer.setAnswerId(1L);

        //member data
        member1 = StubData.getMember1();
        given(memberRepository.save(member1)).willReturn(member1);
        memberRepository.save(member1);
        answer.setMember(member1);
        //answer data
        question1 = StubData.getQuestion();
        given(questionRepository.save(question1)).willReturn(question1);
        questionRepository.save(question1);
        answer.setQuestion(question1);
    }

    @Test
    @Transactional
    void createAnswer() {
        //stubbing
        given(memberRepository.findById(member1.getMemberId())).willReturn(Optional.of(member1));
        given(answerRepository.save(answer)).willReturn(answer);
        given(questionRepository.findById(question1.getQuestionId())).willReturn(Optional.of(question1));


        //when
        answerService.createAnswer(1L, answer);

        Long memberId = answer.getMember().getMemberId();
        Long questionId = answer.getQuestion().getQuestionId();

        Member member = memberRepository.findById(memberId).get();
        Question question = questionRepository.findById(questionId).get();

        //then
        assertThat(member.getAnswers()).contains(answer);
        assertThat(question.getAnswers()).contains(answer);
    }

    @Test
    @Transactional
    void createDuplicateAnswer() {
        createAnswer();

        //given
        Answer wrongAnswer = new Answer("중복 시도", LocalDateTime.now(), null);
        wrongAnswer.setAnswerId(2L);

        wrongAnswer.setMember(StubData.getMember1());
        wrongAnswer.setQuestion(StubData.getQuestion());

        //when
        //then
        assertThatThrownBy(() -> answerService.createAnswer(1L, wrongAnswer))
                .isInstanceOf(AnswerException.class);
    }


    @Test
    @Transactional
    void updateAnswer() {
        //when
        given(answerRepository.save(Mockito.any(Answer.class))).willReturn(Mockito.any(Answer.class));
        answerRepository.save(answer);

        Answer modifiedAnswer = new Answer("수정 답변", null, LocalDateTime.now());
        modifiedAnswer.setAnswerId(1L);
        modifiedAnswer.setMember(StubData.getMember1());
        modifiedAnswer.setQuestion(StubData.getQuestion());

        //when
        given(answerRepository.findById(1L)).willReturn(Optional.of(modifiedAnswer));
        answerService.updateAnswer(modifiedAnswer);
        given(answerRepository.findById(1L)).willReturn(Optional.of(modifiedAnswer));
        Answer foundAnswer = answerRepository.findById(modifiedAnswer.getAnswerId()).orElseThrow();

        //then - 객체 비교하면 modifiedAt 때문에 fail
        assertThat(foundAnswer.getAnswerContent()).isEqualTo(modifiedAnswer.getAnswerContent());
        assertThat(foundAnswer.getMember().getMemberId()).isEqualTo(modifiedAnswer.getMember().getMemberId());

    }

    @Test
    @Transactional
    void updateAnswerByStranger() {
        //given
        memberRepository.save(StubData.getMember2());

        Answer wrongModifiedAnswer = new Answer("수정 답변", null, LocalDateTime.now());

        wrongModifiedAnswer.setAnswerId(1L);
        wrongModifiedAnswer.setMember(StubData.getMember2());
        wrongModifiedAnswer.setQuestion(StubData.getQuestion());

        //when
        //then
        assertThatThrownBy(() -> answerService.updateAnswer(wrongModifiedAnswer))
                .isInstanceOf(AnswerException.class);
    }


    @Test
    void deleteAnswer() {
        //stubbing
        Long answerId = answer.getAnswerId();

        given(answerRepository.findById(answerId)).willReturn(Optional.of(answer));
        answerService.deleteAnswer(answerId);

        given(answerRepository.findById(answerId)).willReturn(null);
        assertThatThrownBy(() -> answerService.deleteAnswer(answerId))
                .isInstanceOf(NullPointerException.class);
    }

}