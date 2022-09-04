package pre045.board_service.util;

import pre045.board_service.member.dto.*;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.token.dto.TokenDto;
import pre045.board_service.member.token.dto.TokenRequestDto;

public class StubData {
    public static class MockMember {
        public static MemberPostDto getPostDto() {
            return MemberPostDto.builder()
                    .email("email@gmail.com")
                    .username("닉네임")
                    .password("thisispassword")
                    .gender("female")
                    .age(20)
                    .build();
        }

        public static MemberLoginDto getLoginDto() {
            return new MemberLoginDto("email@gmail.com", "thisispassword");
        }

        public static MemberPatchDto getPatchDto() {
            return new MemberPatchDto("닉네임", "prePassword", "newPassword");
        }

        public static MemberRecoveryDto getRecoveryDto() {
            return new MemberRecoveryDto("email@gmail.com", "닉네임");
        }

        public static MemberResponseDto getResponseDto() {
            return MemberResponseDto.of(Member.builder()
                    .memberId(1L)
                    .email("email@gmail.com")
                    .username("닉네임")
                    .gender("female")
                    .age(20)
                    .build());
        }

        public static MemberLoginResponseDto getLoginResponseDto() {
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

}
