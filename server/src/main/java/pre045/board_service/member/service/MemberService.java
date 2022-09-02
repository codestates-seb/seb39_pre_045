package pre045.board_service.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.dto.MemberLoginDto;
import pre045.board_service.member.dto.MemberPostDto;
import pre045.board_service.member.dto.MemberResponseDto;
import pre045.board_service.member.token.dto.TokenDto;
import pre045.board_service.member.token.dto.TokenRequestDto;
import pre045.board_service.member.token.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.member.token.jwt.TokenProvider;
import pre045.board_service.member.token.refreshtoken.entity.RefreshToken;
import pre045.board_service.member.token.refreshtoken.repository.RefreshTokenRepository;

import java.util.Optional;

import static pre045.board_service.exception.ExceptionCode.*;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authBuilder;

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;


    //가입
    public MemberResponseDto signup(MemberPostDto postDto) {
        if (memberRepository.existsByEmail(postDto.getEmail())) {
            throw new BusinessLogicException(EMAIL_EXISTS);
        }

        Member member = postDto.toMember(passwordEncoder);

        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDto login(MemberLoginDto loginDto) {
        //email, pw -> Authentication Token
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();


        //email, pw 검증
        Authentication authentication = authBuilder.getObject().authenticate(authenticationToken);


        //토큰 생성
        TokenDto token = tokenProvider.createToken(authentication);


        RefreshToken refreshToken = RefreshToken.builder()
                .tokenKey(authentication.getName()) // tokenKey == memberId 로 설정
                .tokenValue(token.getRefreshToken())
                .build();


        refreshTokenRepository.save(refreshToken);

        return token;
    }

    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new BusinessLogicException(NOT_VALID_REFRESH_TOKEN);
        }

        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());
        RefreshToken refreshToken = refreshTokenRepository.findByTokenKey(authentication.getName())
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_VALID));

        if (!refreshToken.getTokenValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new BusinessLogicException(NOT_MATCH_TOKEN_WITH_MEMBER);
        }

        TokenDto token = tokenProvider.createToken(authentication);

//        refreshToken.updateToken()
        return null;
    }

    public Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);

        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }


}
