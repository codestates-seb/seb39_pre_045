package pre045.board_service.restdocs.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pre045.board_service.answer.dto.AnswerDto;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.comment.AComment.AnswerComment;
import pre045.board_service.comment.QComment.QuestionComment;
import pre045.board_service.dto.MultiResponseDto;
import pre045.board_service.member.dto.*;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.token.dto.TokenDto;
import pre045.board_service.member.token.dto.TokenRequestDto;
import pre045.board_service.question.entity.Question;
import pre045.board_service.vote.question_vote.dto.QuestionVoteResponseDto;
import pre045.board_service.vote.question_vote.entity.QuestionVote;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pre045.board_service.restdocs.util.StubData.MockAnswer.getAnswer;
import static pre045.board_service.restdocs.util.StubData.MockAnswerComment.getAnswerComment;
import static pre045.board_service.restdocs.util.StubData.MockQuestion.getQuestion1;
import static pre045.board_service.restdocs.util.StubData.MockQuestionComment.getQuestionComment;

public class StubData {

    public static class MockMember {
        public static MemberPostDto getMemberPostDto() {
            return MemberPostDto.builder()
                    .email("email@gmail.com")
                    .username("닉네임")
                    .password("thisispassword")
                    .gender("female")
                    .age(20)
                    .build();
        }

        public static MemberLoginDto getMemberLoginDto() {
            return new MemberLoginDto("email@gmail.com", "thisispassword");
        }

        public static MemberPatchDto getMemberPatchDto() {
            return new MemberPatchDto("닉네임", "prePassword", "newPassword");
        }

        public static MemberRecoveryDto getMemberRecoveryDto() {
            return new MemberRecoveryDto("email@gmail.com", "닉네임");
        }

        public static MemberResponseDto getMemberResponseDto() {
            return MemberResponseDto.of(Member.builder()
                    .memberId(1L)
                    .email("email@gmail.com")
                    .username("닉네임")
                    .gender("female")
                    .age(20)
                    .build());
        }


        public static MemberLoginResponseDto getMemberLoginResponseDto() {
            return MemberLoginResponseDto.builder()
                    .memberId(1L)
                    .email("email@gmail.com")
                    .username("닉네임")
                    .gender("female")
                    .age(20)
                    .grantType("bearer")
                    .accessToken("액세스 토큰")
                    .refreshToken("리프레시 토큰")
                    .accessTokenExpiresIn(1662301413150L)
                    .build();
        }

        public static TokenRequestDto getTokenRequestDto() {
            return new TokenRequestDto("액세스 토큰", "리프레시 토큰");
        }

        public static TokenDto getTokenDto() {
            return new TokenDto("bearer", "액세스 토큰", "리프레시 토큰", 1662301413150L);
        }
    }

    public static class MockAnswer {
        public static AnswerDto.Post getAnswerPostDto() {
            return AnswerDto.Post.builder()
                    .question(getQuestion1())
                    .answerContent("답변 내용")
                    .build();
        }

        public static AnswerDto.Patch getAnswerPatchDto() {
            return AnswerDto.Patch.builder()
                    .question(getQuestion1())
                    .answerId(1L)
                    .answerContent("답변 수정 내용")
                    .build();
        }


        public static AnswerDto.Response getAnswerResponseDto() {
            return AnswerDto.Response.builder()
                    .answerId(1L)
                    .answerUsername("답변 작성자")
                    .answerContent("답변 내용")
                    .createdAt(LocalDateTime.of(2022, 9, 5, 13, 36, 0))
                    .modifiedAt(null)
                    .answerComments(null)
                    .adopted(false)
                    .totalVotes(0)
                    .build();
        }

        public static AnswerDto.Response getAnswerEditResponseDto() {
            return AnswerDto.Response.builder()
                    .answerId(1L)
                    .answerUsername("답변 작성자")
                    .answerContent("답변 내용")
                    .createdAt(LocalDateTime.of(2022, 9, 5, 13, 36, 0))
                    .modifiedAt(LocalDateTime.of(2022, 9, 5, 14, 0, 0))
                    .answerComments(null)
                    .adopted(false)
                    .totalVotes(10)
                    .build();
        }

        public static Answer getAnswer() {
            Answer answer = new Answer(1L, "답변 작성자", "답변 내용",
                    LocalDateTime.of(2022, 9, 5, 19, 0, 0),
                    LocalDateTime.of(2022, 9, 5, 20, 0, 0),
                    false, 2, null, null, new ArrayList<>(), new ArrayList<>());

            return answer;
        }

    }

    public static class MockQuestion {
        public static Question getQuestion1() {
            return Question.builder()
                    .questionId(1L)
                    .title("질문 제목1")
                    .questionContent("질문 내용1")
                    .view(10)
                    .checkAdopted(false)
                    .questionUsername("질문 작성자1")
                    .totalVotes(10)
                    .answers(new ArrayList<>())
                    .questionComments(new ArrayList<>())
                    .build();
        }

        public static Question getQuestion2() {
            return Question.builder()
                    .questionId(2L)
                    .title("질문 제목2")
                    .questionContent("질문 내용2")
                    .createdAt(LocalDateTime.of(2022, 9, 5, 18, 0, 0))
                    .modifiedAt(null)
                    .view(20)
                    .checkAdopted(false)
                    .questionUsername("질문 작성자2")
                    .totalVotes(20)
                    .answers(new ArrayList<>())
                    .questionComments(new ArrayList<>())
                    .build();
        }

        public static Question getQuestion3() {
            return Question.builder()
                    .questionId(3L)
                    .title("질문 제목3")
                    .questionContent("질문 내용3")
                    .createdAt(LocalDateTime.of(2022, 9, 5, 18, 0, 0))
                    .modifiedAt(null)
                    .view(30)
                    .checkAdopted(false)
                    .questionUsername("질문 작성자3")
                    .totalVotes(30)
                    .answers(new ArrayList<>())
                    .questionComments(new ArrayList<>())
                    .build();
        }

        public static List<Question> getQuestionList() {
            List<Question> questions = new ArrayList<>();
            Question question1 = getQuestion3();
            Question question2 = getQuestion2();

            Answer answer = getAnswer();
            answer.setQuestion(question1);

            QuestionComment questionComment = getQuestionComment();
            questionComment.setQuestion(question2);
            AnswerComment answerComment = getAnswerComment();
            answerComment.setAnswer(answer);

            questions.add(question1);
            questions.add(question2);

            return questions;
        }


        public static Page<Question> getPageResult(int page, List<Question> foundQuestions) {
            Pageable pageable = PageRequest.of(page, 20);
            final int start = (int) pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), foundQuestions.size());

            Page<Question> pageResult = new PageImpl<>(foundQuestions.subList(start, end), pageable, foundQuestions.size());
            return pageResult;
        }

        public static MultiResponseDto<Question> getMultiResponseDto() {
            List<Question> questionList = getQuestionList();
            Page<Question> pageResult = getPageResult(0, questionList);

            return new MultiResponseDto<>(pageResult.getContent(), pageResult);
        }
    }

    public static class MockQuestionVote {
        public static QuestionVoteResponseDto getQuestionVoteResponseDto(int vote) {
            QuestionVote questionVote = new QuestionVote();
            questionVote.setTotal(vote);
            return QuestionVoteResponseDto.of(questionVote);
        }
    }

    public static class MockQuestionComment {
        public static QuestionComment getQuestionComment() {
            QuestionComment questionComment = new QuestionComment();
            questionComment.setQuestionCommentId(1L);
            questionComment.setQuestionCommentUsername("질문 댓글 작성자");
            questionComment.setQuestionCommentContent("질문 댓글 내용");

            return questionComment;
        }
    }

    public static class MockAnswerComment {
        public static AnswerComment getAnswerComment() {
            AnswerComment answerComment = new AnswerComment();
            answerComment.setAnswerCommentId(1L);
            answerComment.setAnswerCommentUsername("답변 댓글 작성자");
            answerComment.setAnswerCommentContent("답변 댓글 내용");

            return answerComment;
        }
    }



}
