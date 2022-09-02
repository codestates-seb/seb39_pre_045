package pre045.board_service.member.token.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.token.dto.TokenDto;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static pre045.board_service.exception.ExceptionCode.*;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24; // 2주
    private final Key key;

    //yml 파일에 설정한 secret key로 key 값 초기화 - BASE 64, HMAC-SHA
    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    /**
     * 유저 정보를 받아서 토큰 생성
     *
     * @param authentication
     * @return
     */
    public TokenDto createToken(Authentication authentication) {

        //1. 권한 정보 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));


        //만료 시간 계산을 위해 시간 가져옴
        long now = (new Date()).getTime();

        //access, refresh 토큰 만료 시간 지정
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        /**
         * JWT = header(tpy, alg) + payload(토큰에 담을 정보) + signature
         */

        //access token 생성, payload, 알고리즘 지정
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName()) //sub(토큰 제목) : authentication.getName()
                .setExpiration(accessTokenExpiresIn) //exp : 만료 시간
                .claim(AUTHORITIES_KEY, authorities) //auth : authorities (권한 정보)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();


        TokenDto token = TokenDto.builder()
                .grantType(BEARER_TYPE) //bearer
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();


        return token;
    }

    /**
     * 토큰 받아서 유저 정보 반환
     *
     * @param accessToken
     * @return
     */
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaim(accessToken); //토큰에서 payload claim 가져옴

        if (claims.get(AUTHORITIES_KEY) == null) { //토큰 payload에 auth 정보가 없으면
            throw new BusinessLogicException(TOKEN_HAS_NO_AUTH);
        }


        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(",")) //auth ,로 나누는 건 권한이 여러 개니까
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //user 객체로 Authentication 반환
        UserDetails principal = new User(claims.getSubject(), "", authorities); //토큰 이름, pw, 권한

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    //  올바른, 유효한 토큰인지 검증

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException exception) {
            throw new BusinessLogicException(WRONG_JWT_SIGNATURE);
        } catch (ExpiredJwtException e) {
            throw new BusinessLogicException(EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new BusinessLogicException(UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalStateException e) {
            throw new BusinessLogicException(WRONG_JWT_ARGUMENT);
        }
    }

    //access token을 받아 payload - claims를 반환함
    private Claims parseClaim(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
