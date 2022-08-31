package pre045.board_service.vote.answer_vote.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre045.board_service.vote.answer_vote.dto.AnswerVoteDto;
import pre045.board_service.vote.answer_vote.entity.AnswerVote;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AnswerVoteMapper {

    AnswerVote answerVotePostToAnswerVote(AnswerVoteDto.Post postDto);

    AnswerVoteDto.Response answerVoteToAnswerVoteResponse(AnswerVote answerVote);
}
