package pre045.board_service.member.entity;

import lombok.*;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.AComment.AnswerComment;
import pre045.board_service.comment.QComment.QuestionComment;
import pre045.board_service.question.entity.Question;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;
import pre045.board_service.vote.question_vote.entity.QuestionVote;

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
    private List<QuestionComment> questionComments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<AnswerComment> answerComments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<AnswerVote> answerVotes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<QuestionVote> questionVotes = new ArrayList<>();

    public Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
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

    public void addQuestionComment(QuestionComment questionComment) {
        this.questionComments.add(questionComment);

        if (questionComment.getMember() != this) {
            questionComment.setMember(this);
        }
    }

    public void addAnswerComment(AnswerComment answerComment) {
        this.answerComments.add(answerComment);

        if (answerComment.getMember() != this) {
            answerComment.setMember(this);
        }
    }

    public void addAnswerVote(AnswerVote answerVote) {
        this.answerVotes.add(answerVote);

        if (answerVote.getMember() != this) {
            answerVote.setMember(this);
        }
    }

    public void addQuestionVote(QuestionVote questionVote) {
        this.questionVotes.add(questionVote);

        if (questionVote.getMember() != this) {
            questionVote.setMember(this);
        }
    }
}
