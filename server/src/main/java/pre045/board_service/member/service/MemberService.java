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




    //??????
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

    //?????????
    public MemberLoginResponseDto login(MemberLoginDto loginDto) {
        //email, pw -> Authentication Token
        UsernamePasswordAuthenticationToken authenticationToken = toAuthentication(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication;

        //email, pw ??????
        try {
            authentication = authBuilder.getObject().authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new BusinessLogicException(MEMBER_NOT_EXIST);
        }

        //member
        Member foundMember = findVerifiedMember(Long.parseLong(authentication.getName()));

        //?????? ??????
        TokenDto token = tokenProvider.createToken(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .tokenKey(authentication.getName()) // tokenKey == memberId ??? ??????
                .tokenValue(token.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return MemberLoginResponseDto.of(foundMember, token);
    }

    //????????? ?????? ?????????
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        //Refresh Token ??????
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new BusinessLogicException(NOT_VALID_REFRESH_TOKEN);
        }

        //Access Token?????? ?????? ?????? ?????????
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        //memberId??? Refresh Token ?????? ?????????
        RefreshToken refreshToken = refreshTokenRepository.findByTokenKey(authentication.getName())
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_VALID));


        //Refresh Token??? ????????? ?????? ?????? Refresh Token ????????? ???????????? ????????? -> ???????????? ?????? ??????
        if (!refreshToken.getTokenValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new BusinessLogicException(NOT_MATCH_TOKEN_WITH_MEMBER);
        }

        //Refresh ????????? ????????? ?????? -> ?????? ????????? ?????? ?????? ??????
        TokenDto token = tokenProvider.createToken(authentication);

        //DB??? ????????? Refresh ????????? ?????? ????????? ?????? ?????????
        RefreshToken newRefreshToken = refreshToken.updateToken(token.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return token;
    }


    //????????????
    public void logout() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        RefreshToken refreshToken = verifyRefreshToken(memberId.toString());
        refreshTokenRepository.delete(refreshToken);
    }

    //?????? ??????
    public void deleteMember(Long memberId) {
        Long authMemberId = SecurityUtil.getCurrentMemberId();

        if (!memberId.equals(authMemberId)) {
            throw new BusinessLogicException(NOT_MATCH_TOKEN_WITH_MEMBER);
        }


        //?????? ?????? ??????
        Member foundMember = findVerifiedMember(authMemberId);
        memberRepository.delete(foundMember);

        //refresh token ??????
        RefreshToken refreshToken = verifyRefreshToken(authMemberId.toString());
        refreshTokenRepository.delete(refreshToken);
    }

    //?????? ?????? ??????
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
                .ifPresent(password -> foundMember.setPassword(passwordEncoder.encode(patchDto.getNewPassword())));


        return MemberResponseDto.of(memberRepository.save(foundMember));
    }

    //???????????? ??????
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
            helper.setSubject("team45??? stackoverflow clone service ??????????????? ?????? ?????????????");
            helper.setText(content, true);
            helper.setFrom(new InternetAddress(MAIL_FROM, "team45"));
            mailSender.send(message);

            log.info("????????? ????????? ??????????????? ?????????????????????.");

            foundMember.setPassword(passwordEncoder.encode(tempPassword));
            memberRepository.save(foundMember);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.info("????????? ????????? ??????????????????. {}", e.getMessage());
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
