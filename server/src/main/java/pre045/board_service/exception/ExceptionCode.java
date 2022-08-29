package pre045.board_service.exception;


import lombok.Getter;

public enum ExceptionCode {

    //

    //

    //

    QUESTION_NOT_FOUND(404,"Question not found"),

    ANSWER_CANNOT_DELETE(403, "Answer can not delete");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message){
        this.status = code;
        this.message = message;
    }

}
