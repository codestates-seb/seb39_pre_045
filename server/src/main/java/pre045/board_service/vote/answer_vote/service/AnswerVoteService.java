package pre045.board_service.vote.answer_vote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.answer.service.AnswerService;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.member.service.MemberService;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;
import pre045.board_service.vote.answer_vote.repository.AnswerVoteRepository;

import java.util.List;

import static pre045.board_service.exception.ExceptionCode.CANT_DUPLICATE_VOTE;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerVoteRepository answerVoteRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    private final AnswerService answerService;

    public AnswerVote upVote(Long memberId, AnswerVote answerVote) {
        List<Member> all = memberRepository.findAll();

        //중복 추천 방지
        all.stream()
                .filter(member -> !member.getAnswerVotes().isEmpty())
                .filter(duplicate -> duplicate.getMemberId().equals(memberId))
                .findAny()
                .ifPresent(duplicate -> {
                    throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
                });

        if (answerVote.isUp() || answerVote.isDown()) {
            throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
        } else {
            answerVote.setUp(true);

            Member foundMember = memberService.findVerifiedMember(memberId);
            Answer foundAnswer = answerService.verifyExistAnswer(answerVote.getAnswer().getAnswerId());

            int answerTotalVotes = foundAnswer.getTotalVotes() + 1;
            answerVote.setTotal(answerTotalVotes);
            foundAnswer.setTotalVotes(answerTotalVotes);

            answerVote.setMember(foundMember);
            answerVote.setAnswer(foundAnswer);
        }

        return answerVoteRepository.save(answerVote);
    }

    public AnswerVote downVote(Long memberId, AnswerVote answerVote) {
        List<Member> all = memberRepository.findAll();
        Long answerId = answerVote.getAnswer().getAnswerId();

        //중복 추천 방지
        all.stream()
                .filter(member -> !member.getAnswerVotes().isEmpty())
                .filter(duplicate -> duplicate.getMemberId().equals(memberId))
                .findAny()
                .ifPresent(duplicate -> {
                    throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
                });
        if (answerVote.isUp() || answerVote.isDown()) {
            throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
        } else {
            answerVote.setDown(true);

            Member foundMember = memberService.findVerifiedMember(memberId);
            Answer foundAnswer = answerService.verifyExistAnswer(answerVote.getAnswer().getAnswerId());

            int answerTotalVotes = foundAnswer.getTotalVotes() - 1;
            answerVote.setTotal(answerTotalVotes);
            foundAnswer.setTotalVotes(answerTotalVotes);

            answerVote.setMember(foundMember);
            answerVote.setAnswer(foundAnswer);
        }

        return answerVoteRepository.save(answerVote);
    }



}
