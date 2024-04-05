package exception;

/**
 * packageName    : exception
 * fileName       : ErrorCode
 * author         : Yeong-Huns
 * date           : 2024-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04        Yeong-Huns       최초 생성
 */
public enum ErrorCode {

    INVALID_INPUT_VALUE("E1","올바르지 않은 입력값입니다."),
    NOT_FOUND("E2","결과를 찾을 수 없습니다.");


    private final String code;
    private final String message;

    ErrorCode(String code ,String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
