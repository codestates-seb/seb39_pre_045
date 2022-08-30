package pre045.board_service.comment.AComment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pre045.board_service.answer.entity.Answer;
import pre045.board_service.answer.repository.AnswerRepository;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;

import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.question.entity.Question;


import javax.transaction.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class ACommentService {

    private final ACommentRepository aCommentRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;



    public AComment createAComment(Long memberId, AComment aComment) {
        Long answerId = aComment.getAnswer().getAnswerId(); //answer 매핑 해서 받아야함

        Member member = verifyMember(memberId); //멤버 아이디로 작성자 확인
        aComment.setMember(member);

        //answer 매핑
        Answer answer = verifyExistAnswer(answerId);
        aComment.setAnswer(answer);

        aComment.setACommentUsername(member.getUsername());

        return aCommentRepository.save(aComment);

    }

    public AComment updateAComment(Long memberId, AComment aComment) {
        Long aCommentId = aComment.getACommentId();
        AComment foundAComment = verifyExistAComment(aCommentId); // 코멘트 식별

        // 같은 작성자인지 확인?
        Long postMemberId = foundAComment.getMember().getMemberId(); //멤버랑, 퀘스천 메핑,
        verifySameWriter(postMemberId, memberId);

        // 멤버, 엔서, 퀘스천
        Member foundMember = verifyExistMember(memberId);


        Long answerId = aComment.getAnswer().getAnswerId();
        Answer foundAnswer = verifyExistAnswer(answerId);

        //매핑

        foundAComment.setMember(foundMember);

        foundAComment.setAnswer(foundAnswer);

        foundAComment.setACommentContent(aComment.getACommentContent());
        foundAComment.setACommentUsername(foundMember.getUsername());

        return aCommentRepository.save(foundAComment);

    }



    public void deleteComment(Long aCommentId) {
        AComment foundAComment = verifyExistAComment(aCommentId);
        aCommentRepository.delete(foundAComment);
    }


    private void verifySameWriter(Long addMemberId, Long editMemberId) {
        if (!addMemberId.equals(editMemberId)) {
            throw new NullPointerException();
        }
    }

    private Member verifyMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.ANSWER_CANNOT_POST));
    }

    private AComment verifyExistAComment(Long aCommentId) {
        return aCommentRepository.findById(aCommentId).orElseThrow(()-> new NullPointerException());
    }

    private Answer verifyExistAnswer(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new NullPointerException());
    }

    private Member verifyExistMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_CANNOT_POST));
    }



}