package pre045.board_service.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberPatchDto {

    @Nullable
    @Size(min = 2, max = 15, message = "닉네임 길이는 2 이상 15 이하여야 합니다.")
    private String username;

    private String prePassword;

    @Nullable
    @Size(min = 8, max = 20, message = "비밀번호 길이는 8 이상 20 이하여야 합니다.")
    private String newPassword;

}
