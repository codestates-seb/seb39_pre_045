package pre045.board_service.vote.answer_vote.entity;

import lombok.*;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnswerVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerVoteId;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean up;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean down;

    private int total;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;


    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getAnswerVotes().remove(this);
        }
        this.member = member;
        if (!member.getAnswerVotes().contains(this)) {
            member.addAnswerVote(this);
        }
    }


    public void setAnswer(Answer answer) {
        if (this.answer != null) {
            this.answer.getAnswerVotes().remove(this);
        }
        this.answer = answer;
        if (!answer.getAnswerVotes().contains(this)) {
            answer.addAnswerVotes(this);
        }
    }

}
