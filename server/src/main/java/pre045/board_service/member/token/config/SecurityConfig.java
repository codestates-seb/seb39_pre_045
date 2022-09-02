package pre045.board_service.member.token.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pre045.board_service.member.token.jwt.JwtAccessDeniedHandler;
import pre045.board_service.member.token.jwt.JwtAuthenticationEntryPoint;
import pre045.board_service.member.token.jwt.TokenProvider;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // final, @NotNull 붙은 필드의 생성자 자동 생성
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //https 사용함
                .csrf().disable() //리액트에서 token을 local storage에 저장
                .sessionManagement().sessionCreationPolicy(STATELESS) //세션 X

                .and()
                .exceptionHandling()//예외 잡는 중
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //인증 X
                .accessDeniedHandler(jwtAccessDeniedHandler) //인가 X

                .and()
                .authorizeRequests()
                .antMatchers("/members/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
