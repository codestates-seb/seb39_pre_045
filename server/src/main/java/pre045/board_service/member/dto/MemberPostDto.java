package pre045.board_service.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import pre045.board_service.member.token.entity.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static pre045.board_service.member.token.entity.Member.Authority.ROLE_USER;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberPostDto {

    @NotNull
    @Email
    private String email;

    @NotBlank(message = "이름은 공백일 수 없습니다")
    private String username;

    @NotBlank
    private String password;

    @Nullable
    private String gender;

    @Nullable
    private String age;


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

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
