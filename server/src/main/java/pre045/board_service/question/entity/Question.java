package pre045.board_service.question.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.QComment.QuestionComment;
import pre045.board_service.member.token.entity.Member;
import pre045.board_service.vote.question_vote.entity.QuestionVote;

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
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @Column
    private int view;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean checkAdopted;

    @Column(nullable = false)
    private String questionUsername;

    @Column
    private int totalVotes;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore // 리스폰스로 보내지 않아도 됨
    private Member member;

    @JsonManagedReference
    // 이 엔티티를 json 으로 출력할 때 순환참조를 막기 위한 애너테이션
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionComment> questionComments = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonIgnore // 리스폰스로 보내지 않아도 됨
    private List<QuestionVote> questionVotes = new ArrayList<>();


    // dB에서 외래키로 가지고 있어서
    // 스프링 자체에서 양방향 매핑을 해주기 위함
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getQuestions().remove(this);
        }
        this.member = member;
        if (!member.getQuestions().contains(this)) {
            member.addQuestion(this);
        }
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        // this -> answer 자기 자신
        if (answer.getQuestion() != this) {
            answer.setQuestion(this);
        }
    }

    public void addQuestionComment(QuestionComment questionComment) {
        questionComments.add(questionComment);

        if (questionComment.getQuestion() != this) {
            questionComment.setQuestion(this);
        }
    }

    public void addQuestionVote(QuestionVote questionVote) {
        questionVotes.add(questionVote);

        if (questionVote.getQuestion() != this){
            questionVote.setQuestion(this);
        }
    }


}
