package pre045.board_service.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String title;

    private String questionContent;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private int view;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<QuestionVote> questionVotes = new ArrayList<>();


    public void addAnswer(Answer answer) {
        this.answers.add(answer);

        if (answer.getQuestion() != this) {
            answer.setQuestion(this);
        }

    }
}
