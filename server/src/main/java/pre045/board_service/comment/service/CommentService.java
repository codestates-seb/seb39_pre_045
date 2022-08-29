package pre045.board_service.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pre045.board_service.answer.repository.AnswerRepository;
import pre045.board_service.comment.repository.CommentRepository;
import pre045.board_service.comment.entity.Comment;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.MemberRepository;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;


    public Comment createComment(Long memberId, Comment comment) {
        Long answerId = comment.getAnswer().getAnswerId();

        Member member = verifyMember(memberId); //멤버 아이디로 작성자 확인
        comment.setMember(member);

        comment.setCommentUsername(member.getUsername());

        return commentRepository.save(comment);

    }

    public Comment updateComment(Comment comment) {
        Long commentId = comment.getCommentId();

        Comment foundComment = commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException());

        // 같은 작성자인지 확인?
        Long postMemberId = foundComment.getMember().getMemberId();
        Long editMemberId = comment.getMember().getMemberId();

        verifySameWriter(postMemberId, editMemberId);

        foundComment.setCommentContent(comment.getCommentContent());

        return commentRepository.save(foundComment);

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

    private Comment verifyComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException());
    }

    public void deleteComment(Long commentId) {
        Comment foundComment = verifyComment(commentId);
        commentRepository.delete(foundComment);
    }


}