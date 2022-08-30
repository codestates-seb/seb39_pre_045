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
public class QCommentService {

    private final QCommentRepository qCommentRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;


    public QComment createQComment(Long memberId, QComment qComment) {
        Long questionId = qComment.getQuestion().getQuestionId(); //Question 매핑 해서 받아야함

        Member member = verifyMember(memberId); //멤버 아이디로 작성자 확인
        qComment.setMember(member);

        //question 매핑
        Question question = verifyExistQuestion(questionId);
        qComment.setQuestion(question);

        qComment.setQCommentUsername(member.getUsername());

        return qCommentRepository.save(qComment);

    }

    public QComment updateQComment(Long memberId, QComment qComment) {
        Long qCommentId = qComment.getCommentId();
        QComment foundQComment = verifyExistQComment(qCommentId); // 코멘트 식별

        // 같은 작성자인지 확인?
        Long postMemberId = foundQComment.getMember().getMemberId(); //멤버랑, 퀘스천 메핑,
        verifySameWriter(postMemberId, memberId);

        // 멤버, 엔서, 퀘스천
        Member foundMember = verifyExistMember(memberId);
        Long questionId = qComment.getQuestion().getQuestionId();
        Question foundQuestion = verifyExistQuestion(questionId);

//        Long answerId = aComment.getAnswer().getAnswerId();
//        Answer foundAnswer = verifyExistAnswer(answerId);

        //매핑

        foundQComment.setMember(foundMember);
        foundQComment.setQuestion(foundQuestion);

        foundQComment.setQCommentContent(qComment.getQCommentContent());
        foundQComment.setQCommentUsername(foundMember.getUsername());

        return qCommentRepository.save(foundQComment);

    }

    // 퀘스천, 엔서 모두 코멘트 달리는 것으로,

    public void deleteQComment(Long qCommentId) {
        QComment foundQComment = verifyExistQComment(qCommentId);
        qCommentRepository.delete(foundQComment);
    }


    private void verifySameWriter(Long addMemberId, Long editMemberId) {
        if (!addMemberId.equals(editMemberId)) {
            throw new NullPointerException();
        }
    }

    private Member verifyMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new NullPointerException());
    }

    private QComment verifyExistQComment(Long qCommentId) {
        return qCommentRepository.findById(qCommentId).orElseThrow(()-> new NullPointerException());
    }


    private Member verifyExistMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_CANNOT_POST));
    }

    private Question verifyExistQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }


}