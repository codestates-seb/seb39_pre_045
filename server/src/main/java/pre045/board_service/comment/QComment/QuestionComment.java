package pre045.board_service.comment.QComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.member.entity.Member;
import pre045.board_service.question.entity.Question;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionCommentId;

    private String questionCommentContent;

    //추가
    private String questionCommentUsername;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getQuestionComments().remove(this);
        }
        this.member = member;
        if (!member.getQuestionComments().contains(this)) {
            member.addQuestionComment(this);
        }
    }

    public void setQuestion(Question question) {
        if (this.question != null) {
            this.question.getQuestionComments().remove(this);
        }
        this.question = question;
        if (!question.getQuestionComments().contains(this)) {
            question.addQuestionComment(this);
        }
    }
}
