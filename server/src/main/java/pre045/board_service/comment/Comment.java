package pre045.board_service.comment;


import lombok.*;
import pre045.board_service.answer.Answer;
import pre045.board_service.member.Member;
import pre045.board_service.question.Question;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String commentContent;

    //추가
    private String commentUsername;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

}
