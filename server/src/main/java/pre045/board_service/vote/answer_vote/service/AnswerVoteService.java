package pre045.board_service.vote.answer_vote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.answer.service.AnswerService;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.member.service.MemberService;
import pre045.board_service.member.token.config.SecurityUtil;
import pre045.board_service.vote.answer_vote.dto.AnswerVoteResponseDto;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;
import pre045.board_service.vote.answer_vote.repository.AnswerVoteRepository;

import java.util.List;

import static pre045.board_service.exception.ExceptionCode.CANT_DUPLICATE_VOTE;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerVoteRepository answerVoteRepository;
    private final MemberService memberService;
    private final AnswerService answerService;


    public AnswerVoteResponseDto upVote(Long answerId) {
        Answer foundAnswer = answerService.verifyExistAnswer(answerId);

        List<AnswerVote> answerVotes = foundAnswer.getAnswerVotes();
        Long memberId = SecurityUtil.getCurrentMemberId();

        Member foundMember = memberService.findVerifiedMember(memberId);


        answerVotes.stream()
                .filter(answerVote -> answerVote.getMember().getMemberId().equals(memberId))
                .findAny()
                .ifPresent(duplicate -> {
                    throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
                });

        int answerTotalVotes = foundAnswer.getTotalVotes() + 1;
        foundAnswer.setTotalVotes(answerTotalVotes);

        AnswerVote answerVote = new AnswerVote();
        answerVote.setMember(foundMember);
        answerVote.setAnswer(foundAnswer);
        answerVote.setTotal(answerTotalVotes);

        return AnswerVoteResponseDto.of(answerVoteRepository.save(answerVote));

    }

    public AnswerVoteResponseDto downVote(Long answerId) {
        Answer foundAnswer = answerService.verifyExistAnswer(answerId);

        List<AnswerVote> answerVotes = foundAnswer.getAnswerVotes();
        Long memberId = SecurityUtil.getCurrentMemberId();

        Member foundMember = memberService.findVerifiedMember(memberId);


        answerVotes.stream()
                .filter(answerVote -> answerVote.getMember().getMemberId().equals(memberId))
                .findAny()
                .ifPresent(duplicate -> {
                    throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
                });

        int answerTotalVotes = foundAnswer.getTotalVotes() + 1;
        foundAnswer.setTotalVotes(answerTotalVotes);

        AnswerVote answerVote = new AnswerVote();
        answerVote.setMember(foundMember);
        answerVote.setAnswer(foundAnswer);
        answerVote.setTotal(answerTotalVotes);

        return AnswerVoteResponseDto.of(answerVoteRepository.save(answerVote));

    }
}
