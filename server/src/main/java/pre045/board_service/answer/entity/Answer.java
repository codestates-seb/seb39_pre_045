package pre045.board_service.answer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pre045.board_service.comment.AComment.AnswerComment;
import pre045.board_service.member.entity.Member;
import pre045.board_service.question.entity.Question;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String answerUsername;

    @Column(columnDefinition = "TEXT")
    private String answerContent;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean adopted = false;

    private int totalVotes;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @JsonManagedReference
    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<AnswerComment> answerComments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<AnswerVote> answerVotes = new ArrayList<>();

    //테스트용 생성자
    public Answer(String answerContent, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.answerContent = answerContent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getAnswers().remove(this);
        }
        this.member = member;
        if (!member.getAnswers().contains(this)) {
            member.addAnswer(this);
        }
    }

    public void setQuestion(Question question) {
        if (this.question != null) {
            this.question.getAnswers().remove(this);
        }
        this.question = question;
        if (!question.getAnswers().contains(this)) {
            question.addAnswer(this);
        }
    }

    public void addAnswerComments(AnswerComment answerComment) {
        this.answerComments.add(answerComment);

        if (answerComment.getAnswer() != this) {
            answerComment.setAnswer(this);
        }
    }

    public void addAnswerVotes(AnswerVote answerVote) {
        this.answerVotes.add(answerVote);

        if (answerVote.getAnswer() != this) {
            answerVote.setAnswer(this);
        }
    }

    public void setAnswerUsername(String answerUsername) {
        this.answerUsername = answerUsername;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
}
