package pre045.board_service.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import pre045.board_service.answer.Answer;
import pre045.board_service.comment.Comment;
import pre045.board_service.question.Question;
import pre045.board_service.vote.answer_vote.AnswerVote;
import pre045.board_service.vote.question_vote.QuestionVote;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String password;

    private String username;

    private String gender;

    private int age;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<AnswerVote> answerVotes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<QuestionVote> questionVotes = new ArrayList<>();

    public Member(String email, String password, String username, String gender, int age) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.age = age;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);

        if (answer.getMember() != this) {
            answer.setMember(this);
        }
    }

}
