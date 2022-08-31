package pre045.board_service.vote.question_vote.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre045.board_service.vote.question_vote.dto.QuestionVoteDto;
import pre045.board_service.vote.question_vote.entity.QuestionVote;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface QuestionVoteMapper {

    QuestionVote questionVotePostToQuestionVote(QuestionVoteDto.Post postDto);

    QuestionVoteDto.Response questionVoteToQuestionVoteResponse(QuestionVote questionVote);
}
