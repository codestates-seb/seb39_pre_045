package pre045.board_service.vote.question_vote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.service.MemberService;
import pre045.board_service.member.token.config.SecurityUtil;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.service.QuestionService;
import pre045.board_service.vote.question_vote.dto.QuestionVoteResponseDto;
import pre045.board_service.vote.question_vote.entity.QuestionVote;
import pre045.board_service.vote.question_vote.repository.QuestionVoteRepository;

import java.util.List;

import static pre045.board_service.exception.ExceptionCode.CANT_DUPLICATE_VOTE;

@Service
@RequiredArgsConstructor
public class QuestionVoteService {

    private final QuestionVoteRepository questionVoteRepository;
    private final MemberService memberService;
    private final QuestionService questionService;

    public QuestionVoteResponseDto upVote(Long questionId) {
        Question foundQuestion = questionService.findVerifiedQuestionById(questionId);

        List<QuestionVote> questionVotes = foundQuestion.getQuestionVotes();
        Long memberId = SecurityUtil.getCurrentMemberId();

        verifyDuplicateVote(questionVotes, memberId);

        int totalVotes = foundQuestion.getTotalVotes() + 1;

        Member foundMember = memberService.findVerifiedMember(memberId);
        QuestionVote questionVote = mapRelation(foundQuestion, foundMember, totalVotes);

        return QuestionVoteResponseDto.of(questionVote);
    }

    public QuestionVoteResponseDto downVote(Long questionId) {
        Question foundQuestion = questionService.findVerifiedQuestionById(questionId);

        List<QuestionVote> questionVotes = foundQuestion.getQuestionVotes();
        Long memberId = SecurityUtil.getCurrentMemberId();

        verifyDuplicateVote(questionVotes, memberId);

        int totalVotes = foundQuestion.getTotalVotes() - 1;

        Member foundMember = memberService.findVerifiedMember(memberId);
        QuestionVote questionVote = mapRelation(foundQuestion, foundMember, totalVotes);

        return QuestionVoteResponseDto.of(questionVoteRepository.save(questionVote));
    }

    private void verifyDuplicateVote(List<QuestionVote> questionVotes, Long memberId) {
        questionVotes.stream()
                .filter(vote -> vote.getMember().getMemberId().equals(memberId))
                .findAny()
                .ifPresent(duplicate -> {
                    throw new BusinessLogicException(CANT_DUPLICATE_VOTE);
                });
    }

    private QuestionVote mapRelation(Question foundQuestion, Member foundMember, int totalVotes) {
        foundQuestion.setTotalVotes(totalVotes);

        QuestionVote questionVote = new QuestionVote();
        questionVote.setMember(foundMember);
        questionVote.setQuestion(foundQuestion);
        questionVote.setTotal(totalVotes);

        return questionVoteRepository.save(questionVote);
    }

}
