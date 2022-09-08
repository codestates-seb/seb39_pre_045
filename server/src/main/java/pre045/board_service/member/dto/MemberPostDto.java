package pre045.board_service.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import pre045.board_service.member.entity.Member;
import pre045.board_service.validator.EmailNotDuplicate;
import pre045.board_service.validator.UsernameNotDuplicate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pre045.board_service.member.entity.Member.Authority.ROLE_USER;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberPostDto {

    @Email
    @EmailNotDuplicate
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    private String email;

    @Size(min = 2, max = 15, message = "닉네임 길이는 2 이상 15 이하여야 합니다.")
    @UsernameNotDuplicate
    private String username;

    @Size(min = 8, max = 20, message = "비밀번호 길이는 8 이상 20 이하여야 합니다.")
    private String password;

    @Nullable
    private String gender;

    @Nullable
    @Range(min = 1, max = 99, message = "1세 이상 100세 미만이시겠죠....? 범위에 맞게 입력해주세요")
    private Integer age;


    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .username(username)
                .gender(gender)
                .age(age)
                .authority(ROLE_USER)
                .build();
    }

}
