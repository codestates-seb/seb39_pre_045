package pre045.board_service.question.entity;

import lombok.*;
import pre045.board_service.answer.Answer;
import pre045.board_service.comment.Comment;
import pre045.board_service.member.Member;
import pre045.board_service.vote.question_vote.QuestionVote;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String questionContent;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private int view = 0;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionVote> questionVotes = new ArrayList<>();

}
