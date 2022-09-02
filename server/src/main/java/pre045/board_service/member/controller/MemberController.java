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
<<<<<<< Updated upstream
import pre045.board_service.member.token.dto.TokenRequestDto;
=======
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.mapper.MemberMapper;
>>>>>>> Stashed changes
import pre045.board_service.member.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/members")
@Validated
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    public ResponseEntity signup(@RequestBody MemberPostDto postDto){

        return new ResponseEntity<>(new SingleResponseDto<>(memberService.signup(postDto)), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberLoginDto loginDto){

        return new ResponseEntity<>(new SingleResponseDto<>(memberService.login(loginDto)), HttpStatus.OK);
    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody TokenRequestDto tokenRequestDto){

        memberService.logout(tokenRequestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestBody TokenRequestDto tokenRequestDto) {

        return new ResponseEntity<>(new SingleResponseDto<>(memberService.reissue(tokenRequestDto)), HttpStatus.OK);
    }



    @PatchMapping("/{member-id}")
    public ResponseEntity updateMember(@PathVariable("member-id") @Positive long memberId,
                                       @Valid @RequestBody MemberPatchDto memberDtoPatch){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/recovery")
    public ResponseEntity recoveryMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}")
    public ResponseEntity getMyPage(){

        // Todo

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
