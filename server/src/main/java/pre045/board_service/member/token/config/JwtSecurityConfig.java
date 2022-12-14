package pre045.board_service.member.token.config;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pre045.board_service.member.token.filter.JwtExceptionHandlerFilter;
import pre045.board_service.member.token.filter.JwtFilter;
import pre045.board_service.member.token.jwt.TokenProvider;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final JwtExceptionHandlerFilter jwtExceptionHandlerFilter;

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionHandlerFilter, JwtFilter.class);
    }
}
