package pre045.board_service.vote.answer_vote;

import lombok.*;
import pre045.board_service.answer.Answer;
import pre045.board_service.member.Member;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnswerVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aVoteId;

    private int aCount;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean aCheck;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

}
