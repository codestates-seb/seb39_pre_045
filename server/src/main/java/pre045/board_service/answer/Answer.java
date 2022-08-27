package pre045.board_service.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pre045.board_service.comment.Comment;
import pre045.board_service.member.Member;
import pre045.board_service.question.Question;
import pre045.board_service.vote.answer_vote.AnswerVote;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 추후 리팩토링
 * 1. setter 사용 제한
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    //추가함
    private String answerUsername;

    private String answerContent;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    //채택
    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isAdopted;


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

    //테스트용 생성자
    public Answer(String answerContent, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.answerContent = answerContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void addMember(Member member) {
        if (this.member != null) {
            this.member.getAnswers().remove(this);
        }
        this.member = member;
        if (!member.getAnswers().contains(this)) {
            member.addAnswer(this);
        }
    }

    public void addQuestion(Question question) {
        if (this.question != null) {
            this.question.getAnswers().remove(this);
        }
        this.question = question;
        if (!question.getAnswers().contains(this)) {
            question.addAnswer(this);
        }
    }



}
