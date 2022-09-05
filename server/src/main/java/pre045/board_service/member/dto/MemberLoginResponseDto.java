package pre045.board_service.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.token.dto.TokenDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberLoginResponseDto {

    private Long memberId;

    private String email;

    private String username;

    private String gender;

    private Integer age;


    private String grantType;

    private String accessToken;

    private String refreshToken;

    private Long accessTokenExpiresIn;

    public static MemberLoginResponseDto of(Member member, TokenDto tokenDto) {
        return MemberLoginResponseDto.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .username(member.getUsername())
                .gender(member.getGender())
                .age(member.getAge())
                .grantType(tokenDto.getGrantType())
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .accessTokenExpiresIn(tokenDto.getAccessTokenExpiresIn())
                .build();
    }
}
