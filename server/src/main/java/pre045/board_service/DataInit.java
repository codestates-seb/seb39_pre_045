package pre045.board_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.answer.repository.AnswerRepository;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;

@Slf4j
@RequiredArgsConstructor
public class DataInit {
    private final MemberRepository memberRepository;
//    private final QuestionRepository questionRepository;
//
//    private final AnswerRepository answerRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        Member member1 = StubData.getMember1();
        memberRepository.save(member1);

        Member member2 = StubData.getMember2();
        memberRepository.save(member2);

//        Question question1 = StubData.getQuestion1();
//        question1.setMember(member1);
//        question1.setQuestionUsername(member1.getUsername());
//        questionRepository.save(question1);
//
//        Question question2 = StubData.getQuestion2();
//        question2.setMember(member1);
//        question2.setQuestionUsername(member1.getUsername());
//        questionRepository.save(question2);
//
//        Question question3 = StubData.getQuestion3();
//        question3.setMember(member2);
//        question3.setQuestionUsername(member2.getUsername());
//        questionRepository.save(question3);
//
//        Answer answer1 = StubData.getAnswer1();
//        answer1.setMember(member2);
//        answer1.setQuestion(question1);
//        answer1.setAnswerUsername("아롬");
//        answerRepository.save(answer1);

    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    private void init2() {
//        Member member1 = StubData.getMember1();
//        memberRepository.save(member1);
//        Member member2 = StubData.getMember2();
//        memberRepository.save(member2);
//    }
}
