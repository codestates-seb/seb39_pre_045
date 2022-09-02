package pre045.board_service.member.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre045.board_service.member.dto.MemberPatchDto;
import pre045.board_service.member.dto.MemberPostDto;
import pre045.board_service.member.token.entity.Member;
import pre045.board_service.member.mapper.MemberMapper;
import pre045.board_service.member.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public ResponseEntity signUpMember(@Valid @RequestBody MemberPostDto memberDtoPost){


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity logoutMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity updateMember(@PathVariable("member-id") @Positive long memberId,
                                       @Valid @RequestBody MemberPatchDto memberDtoPatch){
        Member member = mapper.memberPatchToMember(memberDtoPatch);
//        member.setMemberId(memberId);
//
//        Member updateMember =


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
