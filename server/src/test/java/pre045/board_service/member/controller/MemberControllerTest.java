package pre045.board_service.member.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import pre045.board_service.member.dto.*;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.member.service.MemberService;
import pre045.board_service.member.token.dto.TokenDto;
import pre045.board_service.member.token.dto.TokenRequestDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pre045.board_service.util.RestDocsConfig.field;
import static pre045.board_service.util.StubData.MockMember.*;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class MemberControllerTest extends RestDocsTestSupport {
    @MockBean
    protected MemberService memberService;
    @MockBean
    protected MemberRepository memberRepository;

    @Test
    @WithMockUser
    @DisplayName("회원 가입")
    public void signup() throws Exception {
        MemberPostDto postDto = getPostDto();
        String content = gson.toJson(postDto);
        MemberResponseDto responseDto = getResponseDto();

        given(memberService.signup(Mockito.any(MemberPostDto.class))).willReturn(responseDto);

        mockMvc.perform(
                        post("/members/signup")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(postDto.getEmail()))
                .andExpect(jsonPath("$.data.username").value(postDto.getUsername()))
                .andExpect(jsonPath("$.data.gender").value(postDto.getGender()))
                .andExpect(jsonPath("$.data.age").value(postDto.getAge()))
                .andDo(
                        restDocs.document(
                                requestFields(
                                        List.of(
                                                fieldWithPath("email").type(STRING).description("이메일").attributes(field("constraints", "중복 불가, 이메일 형식")),
                                                fieldWithPath("username").type(STRING).description("닉네임").attributes(field("constraints", "중복 불가, 길이 2~15")),
                                                fieldWithPath("password").type(STRING).description("비밀번호").attributes(field("constraints", "길이 8~20")),
                                                fieldWithPath("gender").type(STRING).description("성별").optional(),
                                                fieldWithPath("age").type(NUMBER).description("나이").optional().attributes(field("constraints", "범위 1~99"))
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(OBJECT).description("데이터"),
                                                fieldWithPath("data.memberId").type(NUMBER).description("회원 식별자"),
                                                fieldWithPath("data.email").type(STRING).description("이메일"),
                                                fieldWithPath("data.username").type(STRING).description("닉네임"),
                                                fieldWithPath("data.gender").type(STRING).description("성별").optional(),
                                                fieldWithPath("data.age").type(NUMBER).description("나이").optional()
                                        )
                                )
                        ));
    }

    @Test
    @WithMockUser
    @DisplayName("로그인")
    public void login() throws Exception {
        MemberLoginDto loginDto = getLoginDto();
        String content = gson.toJson(loginDto);
        MemberLoginResponseDto loginResponseDto = getLoginResponseDto();

        given(memberService.login(Mockito.any(MemberLoginDto.class))).willReturn(loginResponseDto);

        mockMvc.perform(
                        post("/members/login")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(loginDto.getEmail()))
                .andDo(
                        restDocs.document(
                                requestFields(
                                        List.of(
                                                fieldWithPath("email").type(STRING).description("이메일").attributes(field("constraints", "이메일 형식")),
                                                fieldWithPath("password").type(STRING).description("비밀번호").attributes(field("constraints", "길이 8~20"))
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(OBJECT).description("데이터"),
                                                fieldWithPath("data.memberId").type(NUMBER).description("회원 식별자"),
                                                fieldWithPath("data.email").type(STRING).description("이메일"),
                                                fieldWithPath("data.username").type(STRING).description("닉네임"),
                                                fieldWithPath("data.gender").type(STRING).description("성별"),
                                                fieldWithPath("data.age").type(NUMBER).description("나이"),
                                                fieldWithPath("data.grantType").type(STRING).description("권한 타입(bearer)"),
                                                fieldWithPath("data.accessToken").type(STRING).description("액세스 토큰"),
                                                fieldWithPath("data.refreshToken").type(STRING).description("리프레시 토큰"),
                                                fieldWithPath("data.accessTokenExpiresIn").type(NUMBER).description("액세스 토큰 만료 시간")
                                        )
                                )
                        ));
    }

    @Test
    @WithMockUser
    @DisplayName("회원 정보 수정")
    public void edit() throws Exception {
        MemberPatchDto patchDto = getPatchDto();
        String content = gson.toJson(patchDto);
        MemberResponseDto responseDto = getResponseDto();

        given(memberService.editInfo(Mockito.any(MemberPatchDto.class))).willReturn(responseDto);

        mockMvc.perform(
                        patch("/members/{member-id}", 1L)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("member-id").description("회원 식별자")
                                ),
                                requestFields(
                                        List.of(
                                                fieldWithPath("username").type(STRING).description("변경할 닉네임").optional().attributes(field("constraints", "중복 불가, 길이 2~15")),
                                                fieldWithPath("prePassword").type(STRING).description("기존 비밀번호").attributes(field("constraints", "길이 8~20")),
                                                fieldWithPath("newPassword").type(STRING).description("변경할 비밀번호").optional().attributes(field("constraints", "길이 8~20"))
                                        )
                                ),
                                responseFields(
                                        fieldWithPath("data").type(OBJECT).description("데이터"),
                                        fieldWithPath("data.memberId").type(NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.email").type(STRING).description("이메일"),
                                        fieldWithPath("data.username").type(STRING).description("닉네임"),
                                        fieldWithPath("data.gender").type(STRING).description("성별").optional(),
                                        fieldWithPath("data.age").type(NUMBER).description("나이").optional()
                                )
                        )
                );

    }

    @Test
    @WithMockUser
    @DisplayName("로그아웃")
    public void logout() throws Exception {
        doNothing().when(memberService).logout();

        mockMvc.perform(
                        post("/members/logout")
                )
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    @DisplayName("비밀번호 찾기")
    public void recovery() throws Exception {
        MemberRecoveryDto recoveryDto = getRecoveryDto();
        String content = gson.toJson(recoveryDto);


        doNothing().when(memberService).recoveryPassword(Mockito.any(MemberRecoveryDto.class));

        mockMvc.perform(
                        get("/members/recovery")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        List.of(
                                                fieldWithPath("email").type(STRING).description("이메일").attributes(field("constraints", "이메일 형식")),
                                                fieldWithPath("username").type(STRING).description("닉네임").attributes(field("constraints", "길이 2~15"))
                                        )
                                )
                        )
                );
    }

    @Test
    @WithMockUser
    @DisplayName("회원 탈퇴")
    public void deleteMember() throws Exception {
        doNothing().when(memberService).deleteMember(1L);

        mockMvc.perform(
                        delete("/members/{member-id}", 1L)
                )
                .andExpect(status().isNoContent())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("member-id").description("회원 식별자")
                                )

                        )
                );
    }

    @Test
    @WithMockUser
    @DisplayName("액세스 토큰 재발급")
    public void reissue() throws Exception {
        TokenRequestDto tokenRequestDto = getTokenRequestDto();
        String content = gson.toJson(tokenRequestDto);
        TokenDto tokenDto = getTokenDto();

        given(memberService.reissue(Mockito.any(TokenRequestDto.class))).willReturn(tokenDto);

        mockMvc.perform(
                        post("/members/reissue")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        List.of(
                                                fieldWithPath("accessToken").type(STRING).description("액세스 토큰"),
                                                fieldWithPath("refreshToken").type(STRING).description("리프레시 토큰").attributes(field("constraints", "만료되지 않아야 함"))
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data.grantType").type(STRING).description("권한 타입(bearer)"),
                                                fieldWithPath("data.accessToken").type(STRING).description("액세스 토큰"),
                                                fieldWithPath("data.refreshToken").type(STRING).description("리프레시 토큰"),
                                                fieldWithPath("data.accessTokenExpiresIn").type(NUMBER).description("액세스 토큰 만료 시간")
                                        )
                                )
                        ));
    }


}
