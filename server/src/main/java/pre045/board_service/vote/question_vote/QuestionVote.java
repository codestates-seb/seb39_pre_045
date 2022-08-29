package pre045.board_service.vote.question_vote;

import lombok.*;
import pre045.board_service.member.Member;
import pre045.board_service.question.entity.Question;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qVoteId;

    private int qCount;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean qCheck;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
}
