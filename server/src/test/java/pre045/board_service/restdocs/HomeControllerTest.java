package pre045.board_service.restdocs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import pre045.board_service.HomeController;
import pre045.board_service.question.entity.Question;
import pre045.board_service.question.repository.QuestionRepository;
import pre045.board_service.restdocs.util.RestDocsTestSupport;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pre045.board_service.restdocs.util.RestDocsConfig.field;
import static pre045.board_service.restdocs.util.StubData.MockQuestion.getQuestionList;

@WebMvcTest(HomeController.class)
@MockBean(JpaMetamodelMappingContext.class)
class HomeControllerTest extends RestDocsTestSupport {
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    @WithMockUser
    @DisplayName("홈 화면")
    void home() throws Exception {
        List<Question> questionList = getQuestionList();

        given(questionRepository.findAll()).willReturn(questionList);

        mockMvc.perform(
                get("/")
                        .param("sort", "")
                )
                .andExpect(status().isOk())
                .andDo(
            restDocs.document(
                    requestParameters(
                            parameterWithName("sort").description("정렬 조건")
                    ),
                    responseFields(
                            List.of(
                                    fieldWithPath("data[]").type(ARRAY).description("데이터"),
                                    fieldWithPath("data.[].questionId").type(NUMBER).description("질문 식별자").attributes(field("constraints", "양수")),
                                    fieldWithPath("data.[].title").type(STRING).description("질문 제목").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].questionContent").type(STRING).description("질문 내용").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].createdAt").type(STRING).description("질문 작성 시간").optional(),
                                    fieldWithPath("data.[].modifiedAt").type(STRING).description("질문 수정 시간").optional(),
                                    fieldWithPath("data.[].view").type(NUMBER).description("질문 조회수"),
                                    fieldWithPath("data.[].checkAdopted").type(BOOLEAN).description("질문 채택 여부"),
                                    fieldWithPath("data.[].questionUsername").type(STRING).description("질문 작성자").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].totalVotes").type(NUMBER).description("질문 추천수"),
                                    fieldWithPath("data.[].questionComments.[].questionCommentId").type(NUMBER).description("질문 댓글 식별자").attributes(field("constraints", "양수")),
                                    fieldWithPath("data.[].questionComments.[].questionCommentContent").type(STRING).description("질문 댓글 내용").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].questionComments.[].questionCommentUsername").type(STRING).description("질문에 댓글 작성자").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].answers").type(ARRAY).description("질문에 달린 답변"),
                                    fieldWithPath("data.[].answers.[].answerId").type(NUMBER).description("답변 식별자").attributes(field("constraints", "양수")),
                                    fieldWithPath("data.[].answers.[].answerUsername").type(STRING).description("답변 작성자").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].answers.[].answerContent").type(STRING).description("답변 수정 내용").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].answers.[].createdAt").type(STRING).description("답변 작성 시간"),
                                    fieldWithPath("data.[].answers.[].modifiedAt").type(STRING).description("답변 수정 시간").optional(),
                                    fieldWithPath("data.[].answers.[].adopted").type(BOOLEAN).description("채택 여부"),
                                    fieldWithPath("data.[].answers.[].totalVotes").type(NUMBER).description("답변 추천수"),
                                    fieldWithPath("data.[].answers.[].answerComments").type(ARRAY).description("답변에 달린 댓글들"),
                                    fieldWithPath("data.[].answers.[].answerComments.[].answerCommentId").type(NUMBER).description("답변 댓글 식별자").attributes(field("constraints", "양수")),
                                    fieldWithPath("data.[].answers.[].answerComments.[].answerCommentContent").type(STRING).description("답변 댓글 내용").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].answers.[].answerComments.[].answerCommentUsername").type(STRING).description("답변 댓글 작성자").attributes(field("constraints", "Not Blank")),
                                    fieldWithPath("data.[].questionComments").type(ARRAY).description("질문에 달린 댓글들")
                            )
                    )
                ));
    }

}