package pre045.board_service.comment.AComment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.answer.repository.AnswerRepository;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;


import javax.transaction.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class AnswerCommentService {

    private final AnswerCommentRepository answerCommentRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;



    public AnswerComment createAnswerComment(Long memberId, AnswerComment answerComment) {
        Long answerId = answerComment.getAnswer().getAnswerId(); //answer 매핑 해서 받아야함

        Member member = verifyMember(memberId); //멤버 아이디로 작성자 확인
        answerComment.setMember(member);

        //answer 매핑
        Answer answer = verifyExistAnswer(answerId);
        answerComment.setAnswer(answer);

        answerComment.setAnswerCommentUsername(member.getUsername());

        return answerCommentRepository.save(answerComment);

    }

    public AnswerComment updateAnswerComment(Long memberId, AnswerComment answerComment) {
        Long answerCommentId = answerComment.getAnswerCommentId();
        AnswerComment foundAnswerComment = verifyExistAnswerComment(answerCommentId); // 코멘트 식별

        // 같은 작성자인지 확인?
        Long postMemberId = foundAnswerComment.getMember().getMemberId(); //멤버랑, 퀘스천 메핑,
        verifySameWriter(postMemberId, memberId);

        // 멤버, 엔서, 퀘스천
        Member foundMember = verifyExistMember(memberId);


        Long answerId = answerComment.getAnswer().getAnswerId();
        Answer foundAnswer = verifyExistAnswer(answerId);

        //매핑

        foundAnswerComment.setMember(foundMember);

        foundAnswerComment.setAnswer(foundAnswer);

        foundAnswerComment.setAnswerCommentContent(answerComment.getAnswerCommentContent());
        foundAnswerComment.setAnswerCommentUsername(foundMember.getUsername());

        return answerCommentRepository.save(foundAnswerComment);

    }



    public void deleteAnswerComment(Long answerCommentId) {
        AnswerComment foundAnswerComment = verifyExistAnswerComment(answerCommentId);
        answerCommentRepository.delete(foundAnswerComment);
    }


    private void verifySameWriter(Long addMemberId, Long editMemberId) {
        if (!addMemberId.equals(editMemberId)) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_CANNOT_EDIT);
        }
    }

    private Member verifyMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    private AnswerComment verifyExistAnswerComment(Long answerCommentId) {
        return answerCommentRepository.findById(answerCommentId).orElseThrow(()-> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    private Answer verifyExistAnswer(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    private Member verifyExistMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }



}