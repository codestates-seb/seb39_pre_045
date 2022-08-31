package pre045.board_service.comment.QComment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;



    public QuestionComment createQuestionComment(Long memberId, QuestionComment questionComment) {
        Long questionId = questionComment.getQuestion().getQuestionId(); //answer 매핑 해서 받아야함

        Member member = verifyMember(memberId); //멤버 아이디로 작성자 확인
        questionComment.setMember(member);

        //question 매핑
        Question question = verifyExistQuestion(questionId);
        questionComment.setQuestion(question);

        questionComment.setQuestionCommentUsername(member.getUsername());

        return questionCommentRepository.save(questionComment);

    }

    public QuestionComment updateQuestionComment(Long memberId, QuestionComment questionComment) {
        Long questionCommentId = questionComment.getQuestionCommentId();
        QuestionComment foundQuestionComment = verifyExistQuestionComment(questionCommentId); // 코멘트 식별

        // 같은 작성자인지 확인?
        Long postMemberId = foundQuestionComment.getMember().getMemberId(); //멤버랑, 퀘스천 메핑,
        verifySameWriter(postMemberId, memberId);

        // 멤버, 엔서, 퀘스천
        Member foundMember = verifyExistMember(memberId);


        Long questionId = questionComment.getQuestion().getQuestionId();
        Question foundQuestion = verifyExistQuestion(questionId);

        //매핑

        foundQuestionComment.setMember(foundMember);

        foundQuestionComment.setQuestion(foundQuestion);

        foundQuestionComment.setQuestionCommentContent(questionComment.getQuestionCommentContent());
        foundQuestionComment.setQuestionCommentUsername(foundMember.getUsername());

        return questionCommentRepository.save(foundQuestionComment);

    }



    public void deleteQuestionComment(Long questionCommentId) {
        QuestionComment foundQuestionComment = verifyExistQuestionComment(questionCommentId);
        questionCommentRepository.delete(foundQuestionComment);
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

    private QuestionComment verifyExistQuestionComment(Long questionCommentId) {
        return questionCommentRepository.findById(questionCommentId).orElseThrow(()-> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    //존재하는 질문인지 확인
    private Question verifyExistQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    private Member verifyExistMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }



}
