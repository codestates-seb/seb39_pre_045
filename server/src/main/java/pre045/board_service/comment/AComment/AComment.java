package pre045.board_service.comment.AComment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import pre045.board_service.answer.entity.Answer;

import pre045.board_service.member.entity.Member;
import pre045.board_service.question.entity.Question;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class AComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aCommentId;

    private String aCommentContent;

    //추가
    private String aCommentUsername;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

}
