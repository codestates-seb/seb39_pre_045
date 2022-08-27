package pre045.board_service.answer;

import lombok.*;
import pre045.board_service.comment.Comment;
import pre045.board_service.member.Member;
import pre045.board_service.question.entity.Question;
import pre045.board_service.vote.answer_vote.AnswerVote;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String answerContent;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<AnswerVote> answerVotes = new ArrayList<>();


}
