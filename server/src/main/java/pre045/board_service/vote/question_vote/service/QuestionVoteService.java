package pre045.board_service.vote.question_vote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.token.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.member.service.MemberService;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.service.QuestionService;
import pre045.board_service.vote.question_vote.entity.QuestionVote;
import pre045.board_service.vote.question_vote.repository.QuestionVoteRepository;

import java.util.List;

import static pre045.board_service.exception.ExceptionCode.CANT_DUPLICATE_VOTE;

@Service
@RequiredArgsConstructor
public class QuestionVoteService {

    private final QuestionVoteRepository questionVoteRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    private final QuestionService questionService;

    public QuestionVote upVote(Long memberId, QuestionVote questionVote) {
        List<Member> all = memberRepository.findAll();

        //중복 추천 방지
        all.stream()
                .filter(member -> !member.getQuestionVotes().isEmpty())
                .filter(duplicate -> duplicate.getMemberId().equals(memberId))
                .findAny()
                .ifPresent(duplicate -> {
                    throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
                });

        if (questionVote.isUp() || questionVote.isDown()) {
            throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
        } else {
            questionVote.setUp(true);

            Member foundMember = memberService.findVerifiedMember(memberId);
            Question foundQuestion = questionService.findVerifiedQuestionById(questionVote.getQuestion().getQuestionId());

            int answerTotalVotes = foundQuestion.getTotalVotes() + 1;
            questionVote.setTotal(answerTotalVotes);
            foundQuestion.setTotalVotes(answerTotalVotes);

            questionVote.setMember(foundMember);
            questionVote.setQuestion(foundQuestion);
        }

        return questionVoteRepository.save(questionVote);
    }


    public QuestionVote downVote(Long memberId, QuestionVote questionVote) {
        List<Member> all = memberRepository.findAll();

        //중복 추천 방지
        all.stream()
                .filter(member -> !member.getQuestionVotes().isEmpty())
                .filter(duplicate -> duplicate.getMemberId().equals(memberId))
                .findAny()
                .ifPresent(duplicate -> {
                    throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
                });

        if (questionVote.isUp() || questionVote.isDown()) {
            throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
        } else {
            questionVote.setDown(true);

            Member foundMember = memberService.findVerifiedMember(memberId);
            Question foundQuestion = questionService.findVerifiedQuestionById(questionVote.getQuestion().getQuestionId());

            int answerTotalVotes = foundQuestion.getTotalVotes() - 1;
            questionVote.setTotal(answerTotalVotes);
            foundQuestion.setTotalVotes(answerTotalVotes);

            questionVote.setMember(foundMember);
            questionVote.setQuestion(foundQuestion);
        }

        return questionVoteRepository.save(questionVote);
    }



}
