package pre045.board_service.vote.answer_vote.entity;

import lombok.*;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AVote {

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
