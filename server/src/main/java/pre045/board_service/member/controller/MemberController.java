package pre045.board_service.member.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.dto.SingleResponseDto;
import pre045.board_service.member.dto.MemberLoginDto;
import pre045.board_service.member.dto.MemberPatchDto;
import pre045.board_service.member.dto.MemberPostDto;
import pre045.board_service.member.dto.MemberRecoveryDto;
import pre045.board_service.member.token.dto.TokenRequestDto;
import pre045.board_service.member.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/members")
@Validated
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입
     * @param postDto - email, username, password, (gender, age)
     * @return - email, username, password
     */
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid MemberPostDto postDto){

        return new ResponseEntity<>(new SingleResponseDto<>(memberService.signup(postDto)), HttpStatus.CREATED);
    }


    /**
     * 로그인
     * @param loginDto - email, password
     * @return - grantType(bearer), accessToken, refreshToken, accessTokenExpiresIn
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDto loginDto){

        return new ResponseEntity<>(new SingleResponseDto<>(memberService.login(loginDto)), HttpStatus.OK);
    }


    /**
     * 로그아웃
     * @return - 204
     *
     * client에서 accessToken 삭제해주세요
     */
    @PostMapping("/logout")
    public ResponseEntity logout(){

        memberService.logout();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * 액세스 토큰 재발급
     * @param tokenRequestDto - accessToken, refreshToken
     * @return - grantType(bearer), accessToken, refreshToken, accessTokenExpiresIn
     */
    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestBody TokenRequestDto tokenRequestDto) {

        return new ResponseEntity<>(new SingleResponseDto<>(memberService.reissue(tokenRequestDto)), HttpStatus.OK);
    }

    /**
     * 회원 탈퇴
     * @return - 204
     *
     * client에서 accessToken 삭제해주세요
     */
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") Long memberId){

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 회원 정보 수정
     * @param memberId -
     * @param patchDto - username(nullable), newPassword(nullable), prePassword
     * @return - email, username, (gender, age)
     */
    @PatchMapping("/{member-id}")
    public ResponseEntity updateMember(@PathVariable("member-id") @Positive Long memberId,
                                       @Valid @RequestBody MemberPatchDto patchDto){

        return new ResponseEntity<>(new SingleResponseDto<>(memberService.editInfo(patchDto)), HttpStatus.OK);
    }

    /**
     * 비밀번호 찾기
     * @param recoveryDto - email, username
     * @return - 200
     */
    @PostMapping("/recovery")
    public ResponseEntity recoveryMember(@RequestBody MemberRecoveryDto recoveryDto){

        memberService.recoveryPassword(recoveryDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
