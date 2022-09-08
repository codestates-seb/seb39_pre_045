package pre045.board_service.comment.AComment;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import pre045.board_service.answer.entity.Answer;

import pre045.board_service.member.entity.Member;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnswerComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerCommentId;

    @Column(columnDefinition = "TEXT")
    private String answerCommentContent;

    //추가
    private String answerCommentUsername;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;


    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getAnswerComments().remove(this);
        }
        this.member = member;
        if (!member.getAnswerComments().contains(this)) {
            member.addAnswerComment(this);
        }
    }

    public void setAnswer(Answer answer) {
        if (this.answer != null) {
            this.answer.getAnswerComments().remove(this);
        }
        this.answer = answer;
        if (!answer.getAnswerComments().contains(this)) {
            answer.addAnswerComments(this);
        }
    }
}
