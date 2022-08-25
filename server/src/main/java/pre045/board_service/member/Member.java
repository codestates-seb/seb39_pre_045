package pre045.board_service.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private AnswerVote answerVote;

    @OneToOne(mappedBy = "member")
    private QuestionVote questionVote;

    public Member(String email, String password, String username, String gender, int age) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.age = age;
    }
}
