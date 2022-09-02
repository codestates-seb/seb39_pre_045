package pre045.board_service.member.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre045.board_service.member.dto.MemberPatchDto;
import pre045.board_service.member.dto.MemberPostDto;
import pre045.board_service.member.dto.MemberResponseDto;
import pre045.board_service.member.entity.Member;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T16:28:12+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberPostDto memberDtoPost) {
        if ( memberDtoPost == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.email( memberDtoPost.getEmail() );
        member.password( memberDtoPost.getPassword() );
        member.username( memberDtoPost.getUsername() );
        member.gender( memberDtoPost.getGender() );
        member.age( memberDtoPost.getAge() );

        return member.build();
    }

    @Override
    public Member memberPatchToMember(MemberPatchDto memberDtoPatch) {
        if ( memberDtoPatch == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.password( memberDtoPatch.getPassword() );
        member.username( memberDtoPatch.getUsername() );

        return member.build();
    }

    @Override
    public MemberResponseDto memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDto.MemberResponseDtoBuilder memberResponseDto = MemberResponseDto.builder();

        memberResponseDto.email( member.getEmail() );
        memberResponseDto.username( member.getUsername() );
        memberResponseDto.gender( member.getGender() );
        memberResponseDto.age( member.getAge() );

        return memberResponseDto.build();
    }

    @Override
    public List<MemberResponseDto> membersToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberResponseDto> list = new ArrayList<MemberResponseDto>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }
}
