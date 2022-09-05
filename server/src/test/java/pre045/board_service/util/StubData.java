package pre045.board_service.util;

import pre045.board_service.answer.dto.AnswerDto;
import pre045.board_service.member.dto.*;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.token.dto.TokenDto;
import pre045.board_service.member.token.dto.TokenRequestDto;
import pre045.board_service.question.entity.Question;

import java.time.LocalDateTime;

import static pre045.board_service.util.StubData.MockQuestion.getQuestion;

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
                    .question(getQuestion())
                    .answerContent("답변 내용")
                    .build();
        }

        public static AnswerDto.Patch getAnswerPatchDto() {
            return AnswerDto.Patch.builder()
                    .question(getQuestion())
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

    }

    public static class MockQuestion {
        public static Question getQuestion() {
            return Question.builder()
                    .questionId(1L)
                    .title("질문 제목")
                    .questionContent("질문 내용")
                    .createdAt(null)
                    .modifiedAt(null)
                    .view(10)
                    .checkAdopted(false)
                    .questionUsername("질문 작성자")
                    .totalVotes(10)
                    .build();
        }
    }


}
