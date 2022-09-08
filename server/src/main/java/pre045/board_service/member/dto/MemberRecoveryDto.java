package pre045.board_service.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRecoveryDto {

    @Email
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    private String email;

    @Size(min = 2, max = 15, message = "닉네임 길이는 2 이상 15 이하여야 합니다.")
    private String username;

}
