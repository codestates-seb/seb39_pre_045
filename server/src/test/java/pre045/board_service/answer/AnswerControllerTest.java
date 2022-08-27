package pre045.board_service.answer;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pre045.board_service.member.Member;
import pre045.board_service.question.Question;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @Autowired
    private AnswerMapper mapper;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private AnswerRepository answerRepository;

    private ResultActions post;
    private AnswerDto.Post postDto;

    private Answer answer;
    private Member member;
    private Question question;

    @BeforeEach
    void init() throws Exception {
        member = StubData.getMember1();
        question = StubData.getQuestion();
        postDto = new AnswerDto.Post(question, "답변 내용입니다");
        answer = mapper.answerPostToAnswer(postDto);
        answer.setAnswerId(1L);

        given(answerService.createAnswer(1L, Mockito.any(Answer.class))).willReturn(answer);

        String content = gson.toJson(postDto);

        post = mockMvc.perform(
                post("/answers/{member-id}/add", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
    }

    @Test
    void addAnswer() throws Exception {
        AnswerDto.Response response = mapper.answerToAnswerResponse(answer);

        post
                .andExpect(status().isCreated())
                .andExpect(jsonPath("answerUsername").value(response.getAnswerUsername()))
                .andExpect(jsonPath("answerContent").value(response.getAnswerContent()))
                .andReturn();
    }

    @Test
    void editAnswer() throws Exception {
        AnswerDto.Patch patchDto = new AnswerDto.Patch(1L, member, question, "답변 수정 내용입니다아");
        Answer editedAnswer = mapper.answerPatchToAnswer(patchDto);
        editedAnswer.setModifiedAt(LocalDateTime.now()); //

        given(answerService.updateAnswer(editedAnswer)).willReturn(editedAnswer);

        String content = gson.toJson(patchDto);

        ResultActions patch = mockMvc.perform(
                patch("/answers/{answer-id}/edit", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        AnswerDto.Response response = mapper.answerToAnswerResponse(editedAnswer);

        patch.andExpect(status().isOk())
                .andExpect(jsonPath("answerUsername").value(response.getAnswerUsername()))
                .andExpect(jsonPath("answerContent").value(response.getAnswerContent()))
                .andExpect(jsonPath("modifiedAt").value(response.getModifiedAt()));
    }
}