package pre045.board_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    QUESTION_NOT_FOUND(404,"유효한 질문이 아닙니다."),
    CANT_DUPLICATE_VOTE(403, "추천이나 비추천은 한번만 가능합니다."),

    COMMENT_CANNOT_EDIT(403, "댓글 작성자만 수정할 수 있습니다."),

    MEMBER_NOT_FOUND(404, "유효한 작성자가 아닙니다."),

    COMMENT_NOT_FOUND(404, "유효한 댓글이 아닙니다."),

    ANSWER_CANNOT_DELETE(403, "채택된 답변은 삭제할 수 없습니다."),
    ANSWER_DUPLICATE_ADOPT(403, "답변 채택은 한번만 가능합니다."),
    ANSWER_CANNOT_POST(401, "답변은 회원만 작성할 수 있습니다."),
    ANSWER_NOT_FOUND(404, "유효한 답변이 아닙니다."),
    ANSWER_CANNOT_EDIT(403, "답변 작성자만 수정할 수 있습니다."),
    ANSWER_DUPLICATE_POST(403, "이미 등록된 답변이 있습니다. 기존 답변을 수정해주세요."),

    CANT_FOUND_MATCHED_QUESTIONS(404, "검색어와 일치하는 질문을 찾을 수 없습니다."),

    NO_PAGES(404, "페이지의 범위를 벗어났습니다."),

    TOKEN_HAS_NO_AUTH(403, "권한 정보가 없는 토큰입니다"),

    WRONG_JWT_SIGNATURE(403, "잘못된 JWT 서명입니다."),

    EXPIRED_JWT_TOKEN(403, "만료된 JWT 토큰입니다"),

    UNSUPPORTED_JWT_TOKEN(403, "지원되지 않는 JWT 토큰입니다"),

    WRONG_JWT_ARGUMENT(403, "올바르지 않은 JWT 토큰입니다"),

    NO_AUTHENTICATION(401, "인증 정보가 없습니다"),

    EMAIL_EXISTS(400, "이미 가입된 정보가 있는 이메일입니다."),

    MEMBER_NOT_EXIST(404, "입력된 로그인 정보와 일치하는 정보가 없습니다."),

    NOT_VALID_REFRESH_TOKEN(401, "유효하지 않은 Refresh Token입니다."),

    MEMBER_NOT_VALID(401, "로그아웃 된 사용자입니다."),

    NOT_MATCH_TOKEN_WITH_MEMBER(401, "토큰의 유저 정보가 일치하지 않습니다.");


    private final int status;

    private final String message;

}
