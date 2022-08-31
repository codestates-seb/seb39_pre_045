package pre045.board_service.member.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.exception.ExceptionCode;
import pre045.board_service.member.entity.Member;
import pre045.board_service.member.repository.MemberRepository;

import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }






    public Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);

        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }


}
