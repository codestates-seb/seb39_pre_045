package pre045.board_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    QUESTION_NOT_FOUND(404,"유효한 질문이 아닙니다."),

    COMMENT_CANNOT_EDIT(403, "댓글 작성자만 수정할 수 있습니다."),

    MEMBER_NOT_FOUND(404, "유효한 작성자가 아닙니다."),

    COMMENT_NOT_FOUND(404, "유효한 댓글이 아닙니다."),

    ANSWER_CANNOT_DELETE(403, "채택된 답변은 삭제할 수 없습니다."),
    ANSWER_DUPLICATE_ADOPT(403, "답변 채택은 한번만 가능합니다."),
    ANSWER_CANNOT_POST(401, "답변은 회원만 작성할 수 있습니다."),
    ANSWER_NOT_FOUND(404, "유효한 답변이 아닙니다."),
    ANSWER_CANNOT_EDIT(403, "답변 작성자만 수정할 수 있습니다."),
    ANSWER_DUPLICATE_POST(403, "이미 등록된 답변이 있습니다. 기존 답변을 수정해주세요.");


    private final int status;
    private final String message;

}
