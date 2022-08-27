package pre045.board_service;

import pre045.board_service.comment.Comment;
import pre045.board_service.member.Member;
import pre045.board_service.question.Question;

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


    public static Question getQuestion1() {
        Question question = new Question();

        question.setQuestionId(1L);
        question.setTitle("제목1");
        question.setQuestionContent("내용");
        question.setView(20);

        return question;
    }

    public static Question getQuestion2() {
        Question question = new Question();

        question.setQuestionId(2L);
        question.setTitle("제목2");
        question.setQuestionContent("내용");
        question.setView(10);

        return question;
    }

    public static Question getQuestion3() {
        Question question = new Question();

        question.setQuestionId(3L);
        question.setTitle("제목3");
        question.setQuestionContent("내용");
        question.setView(55);

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
