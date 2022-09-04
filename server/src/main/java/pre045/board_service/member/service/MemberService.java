package pre045.board_service.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.dto.*;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.member.token.config.SecurityUtil;
import pre045.board_service.member.token.dto.TokenDto;
import pre045.board_service.member.token.dto.TokenRequestDto;
import pre045.board_service.member.token.jwt.TokenProvider;
import pre045.board_service.member.token.refreshtoken.entity.RefreshToken;
import pre045.board_service.member.token.refreshtoken.repository.RefreshTokenRepository;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

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
    private final SpringTemplateEngine templateEngine;

    private static final String MAIL_FROM = "romchanxx@gmail.com";

    private final JavaMailSender mailSender;




    //가입
    public MemberResponseDto signup(MemberPostDto postDto) {
        if (memberRepository.existsByEmail(postDto.getEmail())) {
            throw new BusinessLogicException(EMAIL_EXISTS);
        }

        if (memberRepository.existsByUsername(postDto.getUsername())) {
            throw new BusinessLogicException(USERNAME_EXISTS);
        }

        Member member = postDto.toMember(passwordEncoder);

        return MemberResponseDto.of(memberRepository.save(member));
    }

    //로그인
    public MemberLoginResponseDto login(MemberLoginDto loginDto) {
        //email, pw -> Authentication Token
        UsernamePasswordAuthenticationToken authenticationToken = toAuthentication(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication;

        //email, pw 검증
        try {
            authentication = authBuilder.getObject().authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new BusinessLogicException(MEMBER_NOT_EXIST);
        }

        //member
        Member foundMember = findVerifiedMember(Long.parseLong(authentication.getName()));

        //토큰 생성
        TokenDto token = tokenProvider.createToken(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .tokenKey(authentication.getName()) // tokenKey == memberId 로 설정
                .tokenValue(token.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return MemberLoginResponseDto.of(foundMember, token);
    }

    //액세스 토큰 재발급
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        //Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new BusinessLogicException(NOT_VALID_REFRESH_TOKEN);
        }

        //Access Token에서 인증 정보 가져옴
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        //memberId로 Refresh Token 정보 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByTokenKey(authentication.getName())
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_VALID));


        //Refresh Token의 정보가 입력 받은 Refresh Token 정보랑 일치하지 않으면 -> 유효하지 않은 거임
        if (!refreshToken.getTokenValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new BusinessLogicException(NOT_MATCH_TOKEN_WITH_MEMBER);
        }

        //Refresh 토큰이 유효한 경우 -> 권한 정보로 토큰 다시 생성
        TokenDto token = tokenProvider.createToken(authentication);

        //DB에 저장된 Refresh 토큰도 새로 생성한 걸로 바꿔줌
        RefreshToken newRefreshToken = refreshToken.updateToken(token.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return token;
    }


    //로그아웃
    public void logout() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        RefreshToken refreshToken = verifyRefreshToken(memberId.toString());
        refreshTokenRepository.delete(refreshToken);
    }

    //회원 탈퇴
    public void deleteMember(Long memberId) {
        Long authMemberId = SecurityUtil.getCurrentMemberId();

        if (!memberId.equals(authMemberId)) {
            throw new BusinessLogicException(NOT_MATCH_TOKEN_WITH_MEMBER);
        }


        //회원 정보 삭제
        Member foundMember = findVerifiedMember(authMemberId);
        memberRepository.delete(foundMember);

        //refresh token 삭제
        RefreshToken refreshToken = verifyRefreshToken(authMemberId.toString());
        refreshTokenRepository.delete(refreshToken);
    }

    //회원 정보 수정
    public MemberResponseDto editInfo(MemberPatchDto patchDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member foundMember = findVerifiedMember(memberId);

        UsernamePasswordAuthenticationToken authenticationToken = toAuthentication(foundMember.getEmail(), patchDto.getPrePassword());

        try {
            authBuilder.getObject().authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new BusinessLogicException(MEMBER_NOT_EXIST);
        }


        Optional.ofNullable(patchDto.getUsername())
                .ifPresent(foundMember::setUsername);
        Optional.ofNullable(patchDto.getNewPassword())
                .ifPresent(foundMember::setPassword);


        return MemberResponseDto.of(memberRepository.save(foundMember));
    }

    //비밀번호 찾기
    public void recoveryPassword(MemberRecoveryDto recoveryDto) {

        Member foundMember = memberRepository.findByEmail(recoveryDto.getEmail())
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_EXIST));

        if (!recoveryDto.getUsername().equals(foundMember.getUsername())) {
            throw new BusinessLogicException(MEMBER_NOT_EXIST);
        }

        String email = foundMember.getEmail();
        String tempPassword = getTempPassword();

        String content = setContext(foundMember.getUsername(), tempPassword);

        MimeMessage message = mailSender.createMimeMessage();


        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            helper.setTo(email);
            helper.setSubject("team45의 stackoverflow clone service 비밀번호를 찾고 계신가요?");
            helper.setText(content, true);
            helper.setFrom(new InternetAddress(MAIL_FROM, "team45"));
            mailSender.send(message);

            log.info("이메일 전송이 성공적으로 완료되었습니다.");

            foundMember.setPassword(passwordEncoder.encode(tempPassword));
            memberRepository.save(foundMember);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.info("이메일 전송에 실패했습니다. {}", e.getMessage());
            throw new BusinessLogicException(EMAIL_SEND_ERROR);
        }

    }

    private String setContext(String username, String tempPassword) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("tempPassword", tempPassword);
        return templateEngine.process("mail", context);
    }

    private String getTempPassword() {
        String tempPassword = UUID.randomUUID().toString().replaceAll("-", "");
        return tempPassword.substring(0, 10);
    }



    public Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);

        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(MEMBER_NOT_FOUND));
    }


    private RefreshToken verifyRefreshToken(String refreshTokenKey) {
        return refreshTokenRepository.findByTokenKey(refreshTokenKey)
                .orElseThrow(() -> new BusinessLogicException(REFRESH_TOKEN_NOT_FOUND));
    }


    public UsernamePasswordAuthenticationToken toAuthentication(String email, String password) {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
