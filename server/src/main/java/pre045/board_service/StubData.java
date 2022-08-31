package pre045.board_service;



import pre045.board_service.answer.entity.Answer;
import pre045.board_service.member.entity.Member;
import pre045.board_service.question.entity.Question;

import java.time.LocalDateTime;

public class StubData {

    //멤버
    public static Member getMember1() {
        Member member = new Member("abcd@gmail.com", "12345", "jp");

        return member;
    }

    public static Member getMember2() {
        Member member = new Member("cdfg@gmail.com", "1q2w3e4r", "hwany");

        return member;
    }

    //질문
    public static Question getQuestion1() {
        Question question = new Question();

        question.setTitle("제목1");
        question.setQuestionContent("내용");
        question.setView(20);
        question.setCreatedAt(LocalDateTime.now());

        return question;
    }

    public static Question getQuestion2() {
        Question question = new Question();

        question.setTitle("제목2");
        question.setQuestionContent("내용");
        question.setView(10);
        question.setCreatedAt(LocalDateTime.now());

        return question;
    }

    public static Question getQuestion3() {
        Question question = new Question();

        question.setTitle("제목3");
        question.setQuestionContent("내용");
        question.setView(55);
        question.setCreatedAt(LocalDateTime.now());

        return question;
    }

    public static Answer getAnswer1() {
//        Answer answer = new Answer("테스트", LocalDateTime.now(), null);
        Answer answer = new Answer();
        return answer;
    }

//
//    //댓글
//    public static Comment getQuestionComment1() {
//        Comment comment = new Comment();
//
//        comment.setCommentId(1L);
//        comment.setCommentContent("질문에 댓글 달기1");
//
//        return comment;
//    }
//    public static Comment getQuestionComment2() {
//        Comment comment = new Comment();
//
//        comment.setCommentId(2L);
//        comment.setCommentContent("질문에 댓글 달기2");
//
//        return comment;
//    }
//
//    public static Comment getAnswerComment1() {
//        Comment comment = new Comment();
//
//        comment.setCommentId(1L);
//        comment.setCommentContent("답변에 댓글 달기1");
//
//        return comment;
//    }
//
//    public static Comment getAnswerComment2() {
//        Comment comment = new Comment();
//
//        comment.setCommentId(1L);
//        comment.setCommentContent("답변에 댓글 달기2");
//
//        return comment;
//    }

}
