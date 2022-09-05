package pre045.board_service.answer.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import pre045.board_service.answer.dto.AnswerDto;
import pre045.board_service.answer.entity.Answer;
import pre045.board_service.answer.mapper.AnswerMapper;
import pre045.board_service.answer.service.AnswerService;
import pre045.board_service.util.RestDocsTestSupport;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pre045.board_service.util.RestDocsConfig.field;
import static pre045.board_service.util.StubData.MockAnswer.*;


@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
class AnswerControllerTest extends RestDocsTestSupport {
    @MockBean
    private AnswerService answerService;
    @MockBean
    private AnswerMapper mapper;


    @Test
    @WithMockUser(username = "1")
    @DisplayName("답변 등록")
    void addAnswer() throws Exception {
        AnswerDto.Post answerPostDto = getAnswerPostDto();
        String content = gson.toJson(answerPostDto);
        AnswerDto.Response answerResponseDto = getAnswerResponseDto();


        given(mapper.answerPostToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());
        given(answerService.createAnswer(Mockito.anyLong(), Mockito.any(Answer.class))).willReturn(new Answer());
        given(mapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(answerResponseDto);


        mockMvc.perform(
                        post("/answers")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        List.of(
                                                fieldWithPath("question").type(OBJECT).description("답변을 등록할 질문").attributes(field("constraints", "Not Null")),
                                                fieldWithPath("question.questionId").type(NUMBER).description("질문 식별자").attributes(field("constraints", "양수")),
                                                fieldWithPath("question.title").type(STRING).description("질문 제목").attributes(field("constraints", "Not Blank")),
                                                fieldWithPath("question.questionContent").type(STRING).description("질문 내용").attributes(field("constraints", "Not Blank")),
                                                fieldWithPath("question.view").type(NUMBER).description("질문 조회수"),
                                                fieldWithPath("question.checkAdopted").type(BOOLEAN).description("질문 채택 여부"),
                                                fieldWithPath("question.questionUsername").type(STRING).description("질문 작성자").attributes(field("constraints", "Not Blank")),
                                                fieldWithPath("question.totalVotes").type(NUMBER).description("질문 추천수"),
                                                fieldWithPath("answerContent").type(STRING).description("답변 내용").attributes(field("constraints", "Not Blank"))
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data.answerId").type(NUMBER).description("답변 식별자"),
                                                fieldWithPath("data.answerUsername").type(STRING).description("답변 작성자"),
                                                fieldWithPath("data.answerContent").type(STRING).description("답변 내용"),
                                                fieldWithPath("data.createdAt").type(STRING).description("답변 작성 시간"),
                                                fieldWithPath("data.adopted").type(BOOLEAN).description("채택 여부 (false)"),
                                                fieldWithPath("data.totalVotes").type(NUMBER).description("답변 추천수 (0)")
                                        )
                                )
                        )
                );


    }

    @Test
    @WithMockUser(username = "1")
    @DisplayName("답변 수정")
    void editAnswer() throws Exception {
        AnswerDto.Patch answerPatchDto = getAnswerPatchDto();
        String content = gson.toJson(answerPatchDto);
        AnswerDto.Response answerResponseDto = getAnswerEditResponseDto();

        given(mapper.answerPatchToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.anyLong(), Mockito.any(Answer.class))).willReturn(new Answer());
        given(mapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(answerResponseDto);

        mockMvc.perform(
                        patch("/answers/{answer-id}", 1L)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("answer-id").description("답변 식별자")
                                ),
                                requestFields(
                                        List.of(
                                                fieldWithPath("question").type(OBJECT).description("답변을 등록할 질문").attributes(field("constraints", "Not Null")),
                                                fieldWithPath("question.questionId").type(NUMBER).description("질문 식별자").attributes(field("constraints", "양수")),
                                                fieldWithPath("question.title").type(STRING).description("질문 제목").attributes(field("constraints", "Not Blank")),
                                                fieldWithPath("question.questionContent").type(STRING).description("질문 내용").attributes(field("constraints", "Not Blank")),
                                                fieldWithPath("question.view").type(NUMBER).description("질문 조회수"),
                                                fieldWithPath("question.checkAdopted").type(BOOLEAN).description("질문 채택 여부"),
                                                fieldWithPath("question.questionUsername").type(STRING).description("질문 작성자").attributes(field("constraints", "Not Blank")),
                                                fieldWithPath("question.totalVotes").type(NUMBER).description("질문 추천수"),
                                                fieldWithPath("answerId").type(NUMBER).description("답변 식별자").attributes(field("constraints", "양수")),
                                                fieldWithPath("answerContent").type(STRING).description("답변 수정 내용").attributes(field("constraints", "Not Blank"))
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data.answerId").type(NUMBER).description("답변 식별자"),
                                                fieldWithPath("data.answerUsername").type(STRING).description("답변 작성자"),
                                                fieldWithPath("data.answerContent").type(STRING).description("답변 수정 내용"),
                                                fieldWithPath("data.createdAt").type(STRING).description("답변 작성 시간"),
                                                fieldWithPath("data.modifiedAt").type(STRING).description("답변 수정 시간"),
                                                fieldWithPath("data.answerComment").type(ARRAY).description("답변에 달린 댓글들").optional(),
                                                fieldWithPath("data.adopted").type(BOOLEAN).description("채택 여부"),
                                                fieldWithPath("data.totalVotes").type(NUMBER).description("답변 추천수")
                                        )
                                )
                        )
                );
    }

    @Test
    @WithMockUser
    @DisplayName("답변 삭제")
    void deleteAnswer() throws Exception {
        doNothing().when(answerService).deleteAnswer(1L);

        mockMvc.perform(
                        delete("/answers/{answer-id}", 1L)
                )
                .andExpect(status().isNoContent())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("answer-id").description("답변 식별자")
                                )
                        )
                );
    }

    @Test
    @WithMockUser
    @DisplayName("답변 채택")
    void adoptAnswer() throws Exception {
        doNothing().when(answerService).adoptAnswer(1L,2L);

        mockMvc.perform(
                    post("/answers/{answer-id}/adopt", 1L)
                )
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("answer-id").description("답변 식별자")
                                )
                        )
                );
    }

}