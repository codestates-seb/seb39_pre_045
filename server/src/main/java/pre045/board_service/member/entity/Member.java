package pre045.board_service.member.entity;

import lombok.*;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.AComment.AComment;
import pre045.board_service.comment.QComment.QComment;
import pre045.board_service.question.entity.Question;
import pre045.board_service.vote.answer_vote.entity.AVote;
import pre045.board_service.vote.question_vote.entity.QVote;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<QComment> qComments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<AComment> aComments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<AVote> answerVotes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<QVote> questionVotes = new ArrayList<>();

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

    public void addQuestion(Question question) {
        this.questions.add(question);

        if (question.getMember() != this) {
            question.setMember(this);
        }
    }

}
