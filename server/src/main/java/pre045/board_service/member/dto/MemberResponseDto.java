package pre045.board_service.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pre045.board_service.member.entity.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private String email;

    private String username;

    private String gender;

    private Integer age;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .username(member.getUsername())
                .gender(member.getGender())
                .age(member.getAge())
                .build();
    }
}

