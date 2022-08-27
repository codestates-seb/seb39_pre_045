package pre045.board_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pre045.board_service.member.Member;
import pre045.board_service.member.MemberRepository;
import pre045.board_service.question.Question;
import pre045.board_service.question.QuestionRepository;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class DataInit {
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        Member member1 = StubData.getMember1();
        memberRepository.save(member1);

        Member member2 = StubData.getMember2();
        memberRepository.save(member2);

        Question question = StubData.getQuestion1();
        question.setCreatedAt(LocalDateTime.now());
        question.setMember(member1);
        questionRepository.save(question);

        Question question2 = StubData.getQuestion2();
        question2.setCreatedAt(LocalDateTime.now());
        question2.setMember(member1);
        questionRepository.save(question2);

        Question question3 = StubData.getQuestion3();
        question3.setCreatedAt(LocalDateTime.now());
        question3.setMember(member2);
        questionRepository.save(question3);

    }
}
