package pre045.board_service.vote.question_vote.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import pre045.board_service.util.RestDocsTestSupport;
import pre045.board_service.vote.question_vote.dto.QuestionVoteResponseDto;
import pre045.board_service.vote.question_vote.service.QuestionVoteService;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pre045.board_service.util.StubData.MockQuestionVote.getQuestionVoteResponseDto;

@WebMvcTest(QuestionVoteController.class)
@MockBean(JpaMetamodelMappingContext.class)
class QuestionVoteControllerTest extends RestDocsTestSupport {
    @MockBean
    QuestionVoteService questionVoteService;

    @Test
    @WithMockUser
    @DisplayName("질문 추천")
    void upVote() throws Exception {
        QuestionVoteResponseDto questionVoteResponseDto = getQuestionVoteResponseDto(1);

        given(questionVoteService.upVote(Mockito.anyLong())).willReturn(questionVoteResponseDto);

        mockMvc.perform(
                post("/questions/{question-id}/up", 1L)
                )
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("question-id").description("질문 식별자")
                                )
                        )
                );
    }

    @Test
    @WithMockUser
    @DisplayName("질문 비추천")
    void downVote() throws Exception {
        QuestionVoteResponseDto questionVoteResponseDto = getQuestionVoteResponseDto(-1);

        given(questionVoteService.downVote(Mockito.anyLong())).willReturn(questionVoteResponseDto);

        mockMvc.perform(
                        post("/questions/{question-id}/down", 1L)
                )
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("question-id").description("질문 식별자")
                                )
                        )
                );
    }

}