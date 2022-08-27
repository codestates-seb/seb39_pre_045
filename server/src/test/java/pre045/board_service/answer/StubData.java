package pre045.board_service.answer;

import org.springframework.beans.factory.annotation.Autowired;
import pre045.board_service.comment.Comment;
import pre045.board_service.comment.CommentRepository;
import pre045.board_service.member.Member;
import pre045.board_service.member.MemberRepository;
import pre045.board_service.question.Question;
import pre045.board_service.question.QuestionRepository;

public class StubData {

    public static Member getMember1() {
        Member member = new Member("arom@gmail.com", "12345", "아롬", "f", 20);
        member.setMemberId(1L);

        return member;
    }

    public static Member getMember2() {
        Member member = new Member("arom2@gmail.com", "f2345", "아롬2", "m", 20);
        member.setMemberId(2L);

        return member;
    }


    public static Question getQuestion() {
        Question question = new Question();

        question.setQuestionId(1L);
        question.setTitle("제목");
        question.setQuestionContent("내용");
        question.setView(20);

        return question;
    }

    public static Comment getComment1() {
        Comment comment = new Comment();

        comment.setCommentId(1L);
        comment.setCommentContent("댓글 내용");

        return comment;
    }

    public static Comment getComment2() {
        Comment comment = new Comment();

        comment.setCommentId(2L);
        comment.setCommentContent("댓글 내용 2");

        return comment;
    }

}
