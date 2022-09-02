package pre045.board_service.member.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre045.board_service.member.dto.MemberPatchDto;
import pre045.board_service.member.dto.MemberPostDto;
import pre045.board_service.member.dto.MemberResponseDto;
import pre045.board_service.member.token.entity.Member;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    Member memberPostToMember(MemberPostDto memberDtoPost);

    Member memberPatchToMember(MemberPatchDto memberDtoPatch);

    MemberResponseDto memberToMemberResponse(Member member);

    List<MemberResponseDto> membersToMemberResponses(List<Member> members);

}
