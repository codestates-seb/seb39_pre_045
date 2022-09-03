package pre045.board_service.member.token.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.token.jwt.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pre045.board_service.exception.ExceptionCode.*;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    //모든 요청 시 한번만 실행하도록 함 (가입/로그인/재발급 시 제외)

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = resolveToken(request);

        //토큰이 null이 아니고 유효하다면
        try {
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Authentication authentication = tokenProvider.getAuthentication(jwt); //권한 가져와서
                SecurityContextHolder.getContext().setAuthentication(authentication); //권한 정보 저장
                //인증 사용자 정보 Principal -> Authentication -> SecurityContext -> SecurityContextHolder
            }
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException exception) {
            throw new BusinessLogicException(WRONG_JWT_SIGNATURE);
        } catch (ExpiredJwtException e) {
            throw new BusinessLogicException(EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new BusinessLogicException(UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalStateException e) {
            throw new BusinessLogicException(WRONG_JWT_ARGUMENT);
        }

        filterChain.doFilter(request, response);
    }

    //요청 헤더에서 토큰 정보 가져오기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER); //Authorization 헤더 가져와서
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
